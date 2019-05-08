package com.espressoshock.drinkle.models;

public enum Country {

    AUSTRIA("AUT", "Austria"), AUSTRALIA("AUS", "Australia"),
    ALBANIA("ALB", "Albania"), ARGENTINA("ARG", "Argentina"),
    BAHAMAAS("BHS", "Bahamas "), BANGLADESH("BGD", "Bangladesh "), BARBADOS("BRB", " Barbados"),
    BELARUS("BLR", "Belarus"), BELGIUM("BEL", "Belfium "),
    BRAZIL("BRA", "Brazil "), BULGARIA("BGR", "Bulgaria "), CAMBODGIA("KHM", "Cambodgia"),
    CANADA("CAN", "Canada "), CHILE("CHL", "Chile "), CHINA("CHN", "China "),
    COLOMBIA("COL", "Colombia "), COSTA_RICA("CRI", "Costa Rica "),
    CROATIA("HRV", "Croatia "), CUBA("CUB", "Cuba "), CURACAO("CUW", "Curacao "),
    CYPRUS("CYP", "Cyprus"), CZECH_REPUBLIC("CZE", "Czech Republic "),
    CONGO("COD", "Congo "), DENMARK("DNK", "Denmark "),
    ECUADOR("ECU", " "), EGYPT("EGY", ""),
    FIJI("FJI", "Fiji "), FINLAND("FIN", "Finland"), FRANCE("FRA", "France"),
    GERMANY("DEU", "Germany "), GIBRALTAR("GIB", "Gibraltar"),
    GREECE("GRC", "Greece"), GREENLAND("GRL", "Greenland "),
    GRENADA("GRD", "Grenada "), HAITI("HTI", "Haiti"),
    HUNGARY("HUN", "Hungary "), INDIA("IND", "India"),
    IRELAND("IRL", "Ireland"), ISRAEL("ISR", "Israel"),
    ITALY("ITA", "Italia"), JAMAICA("JAM", ""),
    JAPAN("JPN", "Japan"), MEXICO("MEX", "Mexico"), MOLDOVA("MDA", "Moldova"),
    MONACO("MCO", "Monaco"), MOROCCO("MAR", "Morocco"),
    NETHERLANDS("NLD", "Netherlands"), NEW_ZEALAND("NZL", "New Zealand"),
    NIGER("NER", "Niger"), NIGERIA("NGA", "Nigeria"),
    NORWAY("NOR", "Norway"), PARAGUAY("PRY", "Paraguay"), PERU("PER", "Peru"),
    PHILIPPINES("PHL", "Philippines"), POLAND("POL", "Poland"),
    PORTUGAL("PRT", "Portugal "), PUERTO_RICO("PRI", "Puerto Rico"),
    ROMANIA("ROU", "Romania"), RUSSIA("RUS", "Russia"), SAMOA("WSM", "Samoa"),
    SAN_MARINO("SMR", ""), SERBIA("SRB", ""),
    SLOVAKIA("SVK", "Slovakia"), SLOVENIA("SVN", "Slovenia"),
    SOMALIA("SOM", "Somalia"), SOUTH_AFRICA("ZAF", "South Africa"), SOUTH_KOREA("KOR", "South Korea"),
    SWAZILAND("SWZ", "Swaziland"), SWEDEN("SWE", "Sweden"),
    SWITZERLAND("CHE", "Switzerland"), THAILAND("THA", "Thailand"),
    TUNISIA("TUN", "Tunisia"), TURKEY("TUR", "Turkey"),
    UKRAINE("UKR", "Ukraine"), UNITED_KINGDOM("GBR", "United Kingdom"),
    UNITED_STATES("USA", "United States"), URUGUAY("URY", "Uruguay"),
    VENEZUELA("VEN", "Venezuela "), VIETNAM("VNM", "Vietnam");


    private final String countryCode;
    private final String countryName;

    Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
