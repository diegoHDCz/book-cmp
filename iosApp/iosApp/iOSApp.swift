import SwiftUI
import SharedLogic

@main
struct iOSApp: App {
    init() {
        KoinIOSKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
