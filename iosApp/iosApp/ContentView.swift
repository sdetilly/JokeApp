import SwiftUI
import shared

struct ContentView: View {
    
    let text: String
    let viewModel: MainViewModel
    
    init(test: String, viewModel: MainViewModel) {
        self.text = test
        self.viewModel = viewModel
    }

	var body: some View {
        Text(text)
        Button(action: {viewModel.getJoke()}) {
            Text("Refresh")
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        let serviceLocator = ServiceLocatorImpl()
        ContentView(test: "This is a test joke", viewModel: serviceLocator.mainViewModel)
	}
}
