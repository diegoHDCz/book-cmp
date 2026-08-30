import SwiftUI

// Paleta de cores extraída da logo "Ajuda Fio" — espelha br.com.ajudafio.core.theme.AppPallet do sharedUI.
enum AppPallet {
    // Azul (faixa "Ajuda")
    static let primaryColor = Color(red: 0x04 / 255, green: 0x60 / 255, blue: 0xC5 / 255)
    static let primaryColorLight = Color(red: 0x09 / 255, green: 0x7E / 255, blue: 0xDA / 255)
    static let primaryColorDark = Color(red: 0x01 / 255, green: 0x45 / 255, blue: 0xAE / 255)

    // Verde (texto/folha "Fio")
    static let secondaryColor = Color(red: 0x1B / 255, green: 0x8A / 255, blue: 0x3C / 255)
    static let secondaryColorLight = Color(red: 0x4A / 255, green: 0xAF / 255, blue: 0x29 / 255)
    static let secondaryColorDark = Color(red: 0x1C / 255, green: 0x6C / 255, blue: 0x17 / 255)

    // Vermelho (coração com cruz de saúde)
    static let accentColor = Color(red: 0xE5 / 255, green: 0x10 / 255, blue: 0x0F / 255)

    // Neutros
    static let backgroundColor = Color(red: 0xED / 255, green: 0xF1 / 255, blue: 0xF3 / 255)
    static let surfaceColor = Color.white
    static let textColor = Color(red: 0x1A / 255, green: 0x1D / 255, blue: 0x1F / 255)
    static let textColorSecondary = Color(red: 0x6B / 255, green: 0x72 / 255, blue: 0x80 / 255)
}
