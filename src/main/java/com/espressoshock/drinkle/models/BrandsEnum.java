package com.espressoshock.drinkle.models;

public enum BrandsEnum {
    ABSOLUTE("Absolute", "Vodka", IngredientCategory.VODKA),GREY_GOOSE("Grey Goose", "Vodka", IngredientCategory.VODKA),
    BLACK_COW("Black Cow", "Vodka", IngredientCategory.VODKA),SMIRNOFF("Smirnoff", "Vodka", IngredientCategory.VODKA),
    STOLICHNAYA("Stolichnaya", "Vodka", IngredientCategory.VODKA),TITOS("Tito's", "Vodka", IngredientCategory.VODKA),
    JEAN_MARC("Jean-Marc", "Vodka", IngredientCategory.VODKA),JACK_DANIELS("Jack Daniel's", "Whiskey", IngredientCategory.WHISKEY),
    JOHNNIE_WALKER("Johnnie Walker", "Whiskey", IngredientCategory.WHISKEY),JIM_BEAM("Jim Beam", "Whiskey", IngredientCategory.WHISKEY),
    GLENFARCLASS("Glenfarclass", "Whiskey", IngredientCategory.WHISKEY),JAMESON("Jameson", "Whiskey", IngredientCategory.WHISKEY),
    GLENFIDDICH("Glenfiddich", "Whiskey", IngredientCategory.WHISKEY),MARTINI("Martini", "Vermouth", IngredientCategory.VERMOUTH),
    CINZANO("Cinzano", "Vermouth", IngredientCategory.VERMOUTH),CONTRATTO("Contratto", "Vermouth", IngredientCategory.VERMOUTH),
    COCCHI("Cocchi", "Vermouth", IngredientCategory.VERMOUTH),BEEFEATER("Beefeater", "Gin", IngredientCategory.GIN),
    GORDONS("Gordon's", "Gin", IngredientCategory.GIN),FORTALEZA("Fortaleza", "Tequila", IngredientCategory.TEQUILA),
    JOSE_CUERVO("Jose Cuervo", "Tequila", IngredientCategory.TEQUILA),HERANCIA_DE_PLATA("Herencia de Plata", "Tequila", IngredientCategory.TEQUILA),
    BACARDI("Bacardi", "Rum", IngredientCategory.RUM),HAVANA_CLUB("Havana Club", "Rum", IngredientCategory.RUM),
    SAINT_JAMES("Saint James", "Rum", IngredientCategory.RUM),GREEN_ISLAND("Green Island", "Rum", IngredientCategory.RUM),
    COGNAC("Cognac", "Cognac", IngredientCategory.BRANDY),ARMAGNAC("Armagnac", "Armagnac", IngredientCategory.BRANDY),
    AMARETTO("Amaretto", "Cream", IngredientCategory.LIQUEUR),BAILEYS("Bailey's", "Cream", IngredientCategory.LIQUEUR),
    COINTREAU("Cointreau", "Orange", IngredientCategory.LIQUEUR),KAHLUA("Kahlua", "Coffee", IngredientCategory.LIQUEUR),
    SAMBUCA("Sambuca", "Anise", IngredientCategory.LIQUEUR),SHERIDANS("Sheridan's", "Coffee", IngredientCategory.LIQUEUR),
    TIA_MARIA("Tia Maria", "Coffee", IngredientCategory.LIQUEUR),MIDNIGHT_ESPRESSO("Midnight Espresso", "Coffee", IngredientCategory.LIQUEUR),
    AMARULA("Amarula", "Marula", IngredientCategory.LIQUEUR),CAROLANS("Carolans", "Cream", IngredientCategory.LIQUEUR),
    CRUZAN("Cruzan", "Cream", IngredientCategory.LIQUEUR),CREME_DE_CASSIS("Creme de cassis", "Blackcurrant", IngredientCategory.LIQUEUR),
    GUAVABERRY("Guavaberry", "Berry", IngredientCategory.LIQUEUR),CURACAO("Curacao", "Bitter Orange", IngredientCategory.LIQUEUR),
    ROSOLIO("Rosolio", "Rose", IngredientCategory.LIQUEUR),TRIPLE_SEC("Triple Sec", "Orange", IngredientCategory.LIQUEUR),
    VISINATA("Visinata", "Sour Cherry", IngredientCategory.LIQUEUR),ANISETTE("Anisette", "Anise", IngredientCategory.LIQUEUR),
    CREME_DE_MENTHE("Creme de menthe", "Mint", IngredientCategory.LIQUEUR),FERNET("Fernet", "Herbs", IngredientCategory.LIQUEUR),
    GALLIANO("Galliano", "Herbs", IngredientCategory.LIQUEUR),MASATICA("Masatica", "Mastic resin", IngredientCategory.LIQUEUR),
    JAGERMEISTER("Jagermeister", "Herbs", IngredientCategory.LIQUEUR),UNICUM("Unicum", "Herbs", IngredientCategory.LIQUEUR),
    DRAMBUIE("Drambuie", "Honey&Scotch", IngredientCategory.LIQUEUR),DISARONNO("Disaronno", "Apricot kernel oil", IngredientCategory.LIQUEUR),
    NOCELLO("Nocello", "Nuts", IngredientCategory.LIQUEUR),ADVOCAAT("Advocaat", "Vanilla & eggyolk", IngredientCategory.LIQUEUR),
    ROCK_RYE("Rock&Rye", "Whiskey&Citrus", IngredientCategory.LIQUEUR),BANANAS("99 bananas", "Bananas", IngredientCategory.LIQUEUR),
    CAMPARI("Campari", "Bitter orange", IngredientCategory.LIQUEUR),GRAND_MARNIER("Grand Marnier", "Orange", IngredientCategory.LIQUEUR),
    LIMONCELLO("Limoncello", "Lemon", IngredientCategory.LIQUEUR),MARASCHINO("Maraschino", "Cherry", IngredientCategory.LIQUEUR),
    MIDORI("Midori", "Melone", IngredientCategory.LIQUEUR),MANZANA_VERDE("Manzana Verde", "Green Apple", IngredientCategory.LIQUEUR),
    NOIAU_DE_POISSY("Noiau de Poissy", "Apricot", IngredientCategory.LIQUEUR),PAMA("Pama", "Pomegranate", IngredientCategory.LIQUEUR),
    SAUVIGNON_BLANC("Sauvignon Blanc", "White Wine", IngredientCategory.WINE),CHARDONNAY("Chardonnay", "White Wine", IngredientCategory.WINE),
    PINOT_GRIS("Pinot Gris", "White Wine", IngredientCategory.WINE),CABERNET_SAUVIGNON("Cabernet Sauvignon", "Red Wine", IngredientCategory.WINE),
    MERLOT("Merlot", "Red Wine", IngredientCategory.WINE),SHIRAZ("Shiraz", "Red Wine", IngredientCategory.WINE),
    PINOT_NOIR("Pinot Noir", "Red Wine", IngredientCategory.WINE),GRENACHE("Grenache", "Red Wine", IngredientCategory.WINE),
    DOM_PERIGNON("Dom Perignon", "Champagne", IngredientCategory.WINE),MOET_CHANDON("Moet&Chandon", "Champagne", IngredientCategory.WINE),
    CRISTAL("Cristal", "Champagne", IngredientCategory.WINE),PERRIER_JOUET("Perrier Jouet", "Champagne", IngredientCategory.WINE),
    PROSECCO("Prosecco", "Sparkling Wine", IngredientCategory.WINE),LOUIS_BOUILOT("Louis Bouilot", "Sparkling Wine", IngredientCategory.WINE),
    DEPREVILLE("Depreville", "Sparkling Wine", IngredientCategory.WINE),MAGNERS("Magners", "Pear^Apple", IngredientCategory.CIDER),
    SOMERSBY("Somersby", "Apple", IngredientCategory.CIDER),POMAGNE("Pomagne", "Apple", IngredientCategory.CIDER),
    BULMERS("Bulmers", "Pear", IngredientCategory.CIDER),FIZZ("Fizz", "Fruits", IngredientCategory.CIDER),
    HEINEKEN("Heineken", "Beer", IngredientCategory.BEER),CARLSBERG("Carlsberg", "Beer", IngredientCategory.BEER),
    BUDWEISER("Budweiser", "Beer", IngredientCategory.BEER),CORONA("Corona", "Beer", IngredientCategory.BEER),
    PAULANER("Paulaner", "Beer", IngredientCategory.BEER),STELLA_ARTIOS("Stella Artios", "Beer", IngredientCategory.BEER),
    BIRRA_MORETTI("Birra Moretti", "Beer", IngredientCategory.BEER),SKOL("Skol", "Beer", IngredientCategory.BEER),
    OTHER_BRAND("Other Brand","Unknown Brand",IngredientCategory.OTHER);

    private final String brandName;
    private final String description;
    private final IngredientCategory productType;

    BrandsEnum(String brandNAme, String description, IngredientCategory productType) {
        this.brandName = brandNAme;
        this.description = description;
        this.productType = productType;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getDescription() {
        return description;
    }


    public IngredientCategory getProductType() {
        return productType;
    }
}
