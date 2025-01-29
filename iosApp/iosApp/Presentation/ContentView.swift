import SwiftUI
import shared

struct ContentView: View {
    
    @State var shouldOpenAbout = false
    @State var shouldOpenSources = false

	var body: some View {
        NavigationStack {
            ArticlesScreen(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenSources = true
                        } label: {
                            Label("Sources", systemImage: "list.bullet").labelStyle(.titleAndIcon)
                        }.popover(isPresented: $shouldOpenSources) {
                            SourcesScreen(viewModel: .init())
                        }
                    }
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }.popover(isPresented: $shouldOpenAbout) {
                            AboutScreen()
                        }
                    }
                }
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
