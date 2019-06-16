package util;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MarathiNumberFormatter {
    private final String MARATHI_SPELLOUT_RULES;
    private final RuleBasedNumberFormat marathiTextFormatter;
    private final NumberFormat marathiNumberFormatter;

    public MarathiNumberFormatter() {
        MARATHI_SPELLOUT_RULES = loadSpelloutRules();
        marathiTextFormatter = new RuleBasedNumberFormat(MARATHI_SPELLOUT_RULES);
        marathiNumberFormatter = NumberFormat.getInstance(new Locale("hi", "IN"));
        marathiNumberFormatter.setGroupingUsed(false);
    }

    private static String loadSpelloutRules() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("x.x: << दशमलव >>;").append("\n")
                .append("-x: ऋण >>;").append("\n")
                .append("x.x: << दशमलव >>;").append("\n")
                .append("-x: ऋण >>;").append("\n")
                .append("0: शून्य;").append("\n")
                .append("1: एक;").append("\n")
                .append("2: दोन;").append("\n")
                .append("3: तीन;").append("\n")
                .append("4: चार;").append("\n")
                .append("5: पाच;").append("\n")
                .append("6: सहा;").append("\n")
                .append("7: सात;").append("\n")
                .append("8: आठ;").append("\n")
                .append("9: नऊ;").append("\n")
                .append("10: दहा;").append("\n")
                .append("11: अकरा;").append("\n")
                .append("12: बारा;").append("\n")
                .append("13: तेरा;").append("\n")
                .append("14: चौदा;").append("\n")
                .append("15: पंधरा;").append("\n")
                .append("16: सोळा;").append("\n")
                .append("17: सतरा;").append("\n")
                .append("18: अठरा;").append("\n")
                .append("19: एकोणीस;").append("\n")
                .append("20: वीस;").append("\n")
                .append("21: एकवीस;").append("\n")
                .append("22: बावीस;").append("\n")
                .append("23: तेवीस;").append("\n")
                .append("24: चोवीस;").append("\n")
                .append("25: पंचवीस;").append("\n")
                .append("26: सव्वीस;").append("\n")
                .append("27: सत्तावीस;").append("\n")
                .append("28: अठ्ठावीस;").append("\n")
                .append("29: एकोणतीस;").append("\n")
                .append("30: तीस;").append("\n")
                .append("31: एकतीस;").append("\n")
                .append("32: बत्तीस;").append("\n")
                .append("33: तेहेतीस;").append("\n")
                .append("34: चौतीस;").append("\n")
                .append("35: पस्तीस;").append("\n")
                .append("36: छत्तीस;").append("\n")
                .append("37: सदतीस;").append("\n")
                .append("38: अडतीस;").append("\n")
                .append("39: एकोणचाळीस;").append("\n")
                .append("40: चाळीस;").append("\n")
                .append("41: एक्केचाळीस;").append("\n")
                .append("42: बेचाळीस;").append("\n")
                .append("43: त्रेचाळीस;").append("\n")
                .append("44: चव्वेचाळीस;").append("\n")
                .append("45: पंचेचाळीस;").append("\n")
                .append("46: सेहेचाळीस;").append("\n")
                .append("47: सत्तेचाळीस;").append("\n")
                .append("48: अठ्ठेचाळीस;").append("\n")
                .append("49: एकोणपन्नास;").append("\n")
                .append("50: पन्नास;").append("\n")
                .append("51: एक्कावन्न;").append("\n")
                .append("52: बावन्न;").append("\n")
                .append("53: त्रेपन्न;").append("\n")
                .append("54: चोपन्न;").append("\n")
                .append("55: पंचावन्न;").append("\n")
                .append("56: छप्पन्न;").append("\n")
                .append("57: सत्तावन्न;").append("\n")
                .append("58: अठ्ठावन्न;").append("\n")
                .append("59: एकोणसाठ;").append("\n")
                .append("60: साठ;").append("\n")
                .append("61: एकसष्ठ;").append("\n")
                .append("62: बासष्ठ;").append("\n")
                .append("63: त्रेसष्ठ;").append("\n")
                .append("64: चौसष्ठ;").append("\n")
                .append("65: पासष्ठ;").append("\n")
                .append("66: सहासष्ठ;").append("\n")
                .append("67: सदुसष्ठ;").append("\n")
                .append("68: अडुसष्ठ;").append("\n")
                .append("69: एकोणसत्तर;").append("\n")
                .append("70: सत्तर;").append("\n")
                .append("71: एक्काहत्तर;").append("\n")
                .append("72: बाहत्तर;").append("\n")
                .append("73: त्र्याहत्तर;").append("\n")
                .append("74 :    चौर्‍याहत्तर;").append("\n")
                .append("75: पंच्याहत्तर;").append("\n")
                .append("76: शहात्तर;").append("\n")
                .append("77: सत्याहत्तर;").append("\n")
                .append("78: अठ्ठ्याहत्तर;").append("\n")
                .append("79: एकोण ऐंशी;").append("\n")
                .append("80: ऐंशी;").append("\n")
                .append("81: एक्क्याऐंशी;").append("\n")
                .append("82: ब्याऐंशी;").append("\n")
                .append("83: त्र्याऐंशी;").append("\n")
                .append("84: चौऱ्याऐंशी;").append("\n")
                .append("85: पंच्याऐंशी;").append("\n")
                .append("86: शहाऐंशी;").append("\n")
                .append("87: सत्त्याऐंशी;").append("\n")
                .append("88: अठ्ठ्याऐंशी;").append("\n")
                .append("89: एकोणनव्वद;").append("\n")
                .append("90: नव्वद;").append("\n")
                .append("91: एक्क्याण्णव;").append("\n")
                .append("92: ब्याण्णव;").append("\n")
                .append("93: त्र्याण्णव;").append("\n")
                .append("94: चौऱ्याण्णव;").append("\n")
                .append("95: पंच्याण्णव;").append("\n")
                .append("96: शहाण्णव;").append("\n")
                .append("97: सत्त्याण्णव;").append("\n")
                .append("98: अठ्ठ्याण्णव;").append("\n")
                .append("99: नव्व्याण्णव;").append("\n")
                .append("100: शंभर;").append("\n")
                .append("101: <<शे[ >>];").append("\n")
                .append("200: <<शे[ >>];").append("\n")
                .append("1000: << हज़ार[ >>];").append("\n")
                .append("100000: << लाख[ >>];").append("\n")
                .append("10000000: << कोटी[ >>];").append("\n")
                .append("1000000000000000000: =#,##,##0=;").append("\n");
        return stringBuilder.toString();
    }

    private String spellOutInMarathi(BigDecimal number) {
        return marathiTextFormatter.format(number);
    }

    private String formatInMarathi(BigDecimal number) {
        return marathiNumberFormatter.format(number);
    }

    public String format(BigDecimal number, boolean isSpellOutTextNeeded) {

        if (!isSpellOutTextNeeded) {
            return formatInMarathi(number);
        }

        return formatInMarathi(number) + ", " + spellOutInMarathi(number);
    }

    public double extractEnglishNumber(final String marathiNumber) throws ParseException {
        return marathiNumberFormatter.parse(marathiNumber).doubleValue();
    }
}