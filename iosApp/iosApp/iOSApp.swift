import SwiftUI
import shared

@main
struct iOSApp: App {
	var body: some Scene {
        let serviceLocator = ServiceLocatorImpl()
        let viewModel = serviceLocator.createMainViewModel()
		WindowGroup {
            ObservingView(publisher: asPublisher(viewModel.jokeLabel)) { joke in
                ContentView(test: joke as String, viewModel: viewModel)
            }
            
		}
	}
}
