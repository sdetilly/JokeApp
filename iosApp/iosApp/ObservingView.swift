import Combine
import Foundation
import shared
import SwiftUI

private class ObservableModel<Observed>: ObservableObject {
    @Published var observed: Observed?

    init(publisher: AnyPublisher<Observed, Never>) {
        publisher
            .compactMap { $0 }
            .receive(on: DispatchQueue.main)
            .assign(to: &$observed)
    }
}

public struct ObservingView<Observed, Content>: View where Content: View {

    @ObservedObject private var model: ObservableModel<Observed>

    private let content: (Observed) -> Content

    public init(publisher: AnyPublisher<Observed, Never>, @ViewBuilder content: @escaping (Observed) -> Content) {
        self.model = ObservableModel(publisher: publisher)
        self.content = content
    }

    public var body: some View {
        let view: AnyView
        if let observed = self.model.observed {
            view = AnyView(content(observed))
        } else {
            view = AnyView(EmptyView())
        }
        return view
    }
}

func asPublisher<T>(_ flow: CFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, Never>>> {
        let subject = PassthroughSubject<T, Never>()
        let closable = flow.watch { next in
            if let next = next {
                subject.send(next)
            }
        }
        return subject.handleEvents(receiveCancel: {
            closable.close()
        })
    }.eraseToAnyPublisher()
}
