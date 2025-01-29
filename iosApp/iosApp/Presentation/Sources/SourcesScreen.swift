//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Matheus Felipe on 29/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SourcesScreen: View {
    
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        
        NavigationStack {
            SourcesScreenList(sources: self.viewModel.sourcesState.sources)
                .navigationTitle("Sources")
                .toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button {
                            dismiss()
                        } label: {
                            Text("done").bold()
                        }
                    }
                }
        }.onAppear {
            viewModel.start()
        }.onDisappear {
            viewModel.close()
        }.refreshable {
            viewModel.refreshSources()
        }
    }
}

private struct SourcesScreenList: View {
    
    let sources: [SourceEntity]
    
    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading, spacing: 16) {
                ForEach(sources, id: \.self) { source in
                    SourcesScreenRow(source: source)
                }
            }.padding(16)
        }
        
    }
}


private struct SourcesScreenRow: View {
    
    let source: SourceEntity
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(source.name)
                .font(.title)
                .fontWeight(.bold)
            Text(source.desc)
                .font(.system(size: 14))
                .fontWeight(.regular)
            HStack(spacing: 4) {
                Spacer()
                Text(source.language)
                    .font(.system(size: 10))
                    .fontWeight(.light)
                    .foregroundColor(.gray)
                Text("-")
                    .font(.system(size: 10))
                    .fontWeight(.light)
                    .foregroundColor(.gray)
                Text(source.country)
                    .font(.system(size: 10))
                    .fontWeight(.light)
                    .foregroundColor(.gray)
            }
        }
    }
}
