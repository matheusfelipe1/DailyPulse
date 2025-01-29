//
//  SourcesViewModelWrapper.swift
//  iosApp
//
//  Created by Matheus Felipe on 29/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

class SourcesViewModelWrapper: ObservableObject {
    
    let viewModel: SourceViewModel
    
    @Published var sourcesState: SourceState
    
    init(
        viewModel: SourceViewModel = ServiceLocator.shared.resolve(SourceViewModel.self)
    ) {
        self.viewModel = viewModel
        self.sourcesState = viewModel.sourceState.value as! SourceState
    }
    
    
    func start() {
        Task {
            let stream = AsyncStream<SourceState> { computation in
                self.viewModel.startObserving { state in
                    computation.yield(state)
                }
                computation.onTermination = { @Sendable _ in
                    self.viewModel.stopObserving()
                }
            }
            
            for await state in stream {
                await MainActor.run {
                    self.sourcesState = state
                }
            }
        }
    }
    
    func close() {
        self.viewModel.stopObserving()
    }
    
    func refreshSources() {
        self.viewModel.getSources(forceFetch: true)
    }
    
}
