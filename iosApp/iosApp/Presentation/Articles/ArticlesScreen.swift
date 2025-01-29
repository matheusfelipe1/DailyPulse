//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Matheus Felipe on 20/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared


struct ArticlesScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    
    var body: some View {
        VStack {
            AppBar()
            if viewModel.articlesState.loading {
                Loader()
            }
            if let errorMessage = viewModel.articlesState.errorMessage {
                ErrorMessage(errorMessage: errorMessage)
            }
            
            if !viewModel.articlesState.articles.isEmpty {
                ScrollView {
                    LazyVStack {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItem(article: article)
                        }
                    }
                }
            }
            
        }.onAppear {
            viewModel.start()
        }.onDisappear {
            viewModel.close()
        }.refreshable {
            viewModel.refreshData()
        }
    }
}


struct AppBar: View {
    var body: some View {
        Text("Articles").font(.largeTitle).fontWeight(.bold)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}
    
struct ErrorMessage: View {
    let errorMessage: String
    
    var body: some View {
        Text(errorMessage).font(.title)
    }
}
    
    
    
struct ArticleItem: View {
    
    let article: ArticleEntity
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!.resizable().aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Cannot render the image")
                } else {
                    ProgressView()
                }
            }
            Text(article.title).font(.title).fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(width: .infinity, alignment: .trailing).foregroundColor(.gray)
            
        }.padding(16)
    }
}



