//
//  ArticlesViewModelWrapper.swift
//  iosApp
//
//  Created by Matheus Felipe on 29/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared


class ArticlesViewModelWrapper: ObservableObject {
    
    let articlesViewModel: ArticlesViewModel
    
    
    @Published var articlesState: ArticlesState
    
    init(
        articlesViewModel: ArticlesViewModel = ServiceLocator.shared.resolve(ArticlesViewModel.self)
    ) {
        self.articlesViewModel = articlesViewModel
        self.articlesState = articlesViewModel.articlesState.value as! ArticlesState
    }
    
    func start()  {
        Task {
            let stream = AsyncStream<ArticlesState> { continuation in
                
                self.articlesViewModel.startObserving { state in
                    continuation.yield(state)
                }
                
                continuation.onTermination = { @Sendable _ in
                    self.articlesViewModel.stopObserving()
                }
            }
            
            for await state in stream {
                await MainActor.run {
                    self.articlesState = state
                }
            }
        }
    }
    
    func close() {
        self.articlesViewModel.stopObserving()
    }
    
    func refreshData() {
        self.articlesViewModel.getArticles(forceFetch: true)
    }
}
