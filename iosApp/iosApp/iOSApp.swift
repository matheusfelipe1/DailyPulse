import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinInitializeKt.doInitKoin()
        
        ServiceLocator.shared.register(
            ArticlesInjector().articlesViewModel,
            for: ArticlesViewModel.self
        )
        
        ServiceLocator.shared.register(
            SourcesInjector().sourcesViewModel,
            for: SourceViewModel.self
        )
    }
    
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
