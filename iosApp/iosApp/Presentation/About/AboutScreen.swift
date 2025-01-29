//
//  AboutScreen.swift
//  iosApp
//
//  Created by Matheus Felipe on 29/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct AboutScreen: View {
    let items = makeItems()
    
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        NavigationStack {
            AboutScreenListView().navigationTitle("About Device")
                .toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button {
                            dismiss()
                        } label: {
                            Text("done").bold()
                        }
                    }
                }
        }
    }
}

struct AboutScreenListView: View {
    
    let items = makeItems()
    
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title)
                        .font(.footnote)
                        .backgroundStyle(.secondary)
                    Text(item.subtitle)
                        .font(.body)
                        .backgroundStyle(.primary)
                    
                }.padding(.vertical, 4)
            }
        }
    }
}

struct RowItem: Hashable {
    let title: String
    let subtitle: String
}


func makeItems() -> [RowItem] {
    let platform = Platform()
    
    return [
        .init(title: "Operation System", subtitle: "\(platform.osName)  \(platform.osVersion)"),
        .init(title: "Device", subtitle: platform.deviceModel),
        .init(title: "Density", subtitle: "@\(platform.density)x"),
    ]
}
