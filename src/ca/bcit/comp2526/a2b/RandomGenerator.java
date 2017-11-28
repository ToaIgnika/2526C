package ca.bcit.comp2526.a2b;

/**
 * RandomGenerator.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public final class RandomGenerator {
    private static final int[] NUMBERS;

    static {
        NUMBERS = new int[] {Integer.valueOf("-1155484576"),
                Integer.valueOf("-723955400"), Integer.valueOf("1033096058"),
                Integer.valueOf("-1690734402"), Integer.valueOf("-1557280266"),
                Integer.valueOf("1327362106"), Integer.valueOf("-1930858313"),
                Integer.valueOf("502539523"), Integer.valueOf("-1728529858"),
                Integer.valueOf("-938301587"), Integer.valueOf("1431162155"),
                Integer.valueOf("1085665355"), Integer.valueOf("1654374947"),
                Integer.valueOf("-1661998771"), Integer.valueOf("-65105105"),
                Integer.valueOf("-73789608"), Integer.valueOf("-518907128"),
                Integer.valueOf("99135751"), Integer.valueOf("-252332814"),
                Integer.valueOf("755814641"), Integer.valueOf("1180918287"),
                Integer.valueOf("1344049776"), Integer.valueOf("553609048"),
                Integer.valueOf("1580443894"), Integer.valueOf("629649304"),
                Integer.valueOf("-1266264776"), Integer.valueOf("99807007"),
                Integer.valueOf("5955764"), Integer.valueOf("-1946737912"),
                Integer.valueOf("39620447"), Integer.valueOf("-152527805"),
                Integer.valueOf("-1877116806"), Integer.valueOf("448784075"),
                Integer.valueOf("1086124775"), Integer.valueOf("-1609984092"),
                Integer.valueOf("1227951724"), Integer.valueOf("1764356251"),
                Integer.valueOf("64111306"), Integer.valueOf("-960731419"),
                Integer.valueOf("-100082026"), Integer.valueOf("-39845375"),
                Integer.valueOf("-1339022546"), Integer.valueOf("2092649110"),
                Integer.valueOf("-568315836"), Integer.valueOf("-1089884900"),
                Integer.valueOf("-81839914"), Integer.valueOf("-1146103148"),
                Integer.valueOf("-1846688624"), Integer.valueOf("-784703072"),
                Integer.valueOf("55004124"), Integer.valueOf("-691960657"),
                Integer.valueOf("1770461755"), Integer.valueOf("-2032810463"),
                Integer.valueOf("-1177788003"), Integer.valueOf("-432352882"),
                Integer.valueOf("-65824064"), Integer.valueOf("575267217"),
                Integer.valueOf("-1949367821"), Integer.valueOf("356750287"),
                Integer.valueOf("798819494"), Integer.valueOf("-92022521"),
                Integer.valueOf("1318001577"), Integer.valueOf("-1192467086"),
                Integer.valueOf("-1412716779"), Integer.valueOf("-1223932479"),
                Integer.valueOf("276053035"), Integer.valueOf("615126903"),
                Integer.valueOf("1542603436"), Integer.valueOf("1988388716"),
                Integer.valueOf("1177882237"), Integer.valueOf("19265476"),
                Integer.valueOf("-1430871151"), Integer.valueOf("307082914"),
                Integer.valueOf("-1333570194"), Integer.valueOf("1496453452"),
                Integer.valueOf("-790542135"), Integer.valueOf("1455004595"),
                Integer.valueOf("-1690249972"), Integer.valueOf("-604059026"),
                Integer.valueOf("-290476856"), Integer.valueOf("-122204761"),
                Integer.valueOf("-1097539750"), Integer.valueOf("-576617062"),
                Integer.valueOf("-2002123957"), Integer.valueOf("-1663951485"),
                Integer.valueOf("193034304"), Integer.valueOf("768747578"),
                Integer.valueOf("1337360486"), Integer.valueOf("934457803"),
                Integer.valueOf("873612482"), Integer.valueOf("-624972850"),
                Integer.valueOf("355564760"), Integer.valueOf("41547336"),
                Integer.valueOf("1781447028"), Integer.valueOf("-1321591463"),
                Integer.valueOf("1081281446"), Integer.valueOf("-982203381"),
                Integer.valueOf("-222544851"), Integer.valueOf("-1233998085"),
                Integer.valueOf("-1331702554"), Integer.valueOf("907249073"),
                Integer.valueOf("206843130"), Integer.valueOf("-931610657"),
                Integer.valueOf("1697401307"), Integer.valueOf("-234791961"),
                Integer.valueOf("-996341162"), Integer.valueOf("61144662"),
                Integer.valueOf("-1638496702"), Integer.valueOf("1693091376"),
                Integer.valueOf("-1667947369"), Integer.valueOf("-627963836"),
                Integer.valueOf("105673451"), Integer.valueOf("-918940699"),
                Integer.valueOf("-300903254"), Integer.valueOf("-28037702"),
                Integer.valueOf("1358495114"), Integer.valueOf("-502062723"),
                Integer.valueOf("2056481282"), Integer.valueOf("731396580"),
                Integer.valueOf("617380302"), Integer.valueOf("-162912754"),
                Integer.valueOf("818693069"), Integer.valueOf("-1184143835"),
                Integer.valueOf("-410943565"), Integer.valueOf("-1385753324"),
                Integer.valueOf("1233308008"), Integer.valueOf("-840115328"),
                Integer.valueOf("-1474241772"), Integer.valueOf("1895913584"),
                Integer.valueOf("1376826901"), Integer.valueOf("1984652857"),
                Integer.valueOf("412901507"), Integer.valueOf("-632101198"),
                Integer.valueOf("2025024491"), Integer.valueOf("-2139603013"),
                Integer.valueOf("-1233011876"), Integer.valueOf("-34604646"),
                Integer.valueOf("-1854979571"), Integer.valueOf("-131984708"),
                Integer.valueOf("-1866233577"), Integer.valueOf("1516579035"),
                Integer.valueOf("1612513822"), Integer.valueOf("203005342"),
                Integer.valueOf("-911714850"), Integer.valueOf("307621155"),
                Integer.valueOf("13453556"), Integer.valueOf("125015832"),
                Integer.valueOf("483675743"), Integer.valueOf("2077347671"),
                Integer.valueOf("37011083"), Integer.valueOf("-120473323"),
                Integer.valueOf("2069609186"), Integer.valueOf("-46741469"),
                Integer.valueOf("1502566715"), Integer.valueOf("-998828580"),
                Integer.valueOf("-1206282339"), Integer.valueOf("-2141482063"),
                Integer.valueOf("-1550311379"), Integer.valueOf("1097472880"),
                Integer.valueOf("1449582151"), Integer.valueOf("1327824311"),
                Integer.valueOf("-1105846005"), Integer.valueOf("-651630279"),
                Integer.valueOf("-1174121238"), Integer.valueOf("223701431"),
                Integer.valueOf("810978550"), Integer.valueOf("43703267"),
                Integer.valueOf("835843581"), Integer.valueOf("1519786882"),
                Integer.valueOf("-772329283"), Integer.valueOf("372536197"),
                Integer.valueOf("-317962831"), Integer.valueOf("-642907139"),
                Integer.valueOf("-706495414"), Integer.valueOf("15792134"),
                Integer.valueOf("-1116133237"), Integer.valueOf("1322391079"),
                Integer.valueOf("-1231319713"), Integer.valueOf("-2011725892"),
                Integer.valueOf("1299386162"), Integer.valueOf("-348690342"),
                Integer.valueOf("1341976962"), Integer.valueOf("1190607957"),
                Integer.valueOf("-262501494"), Integer.valueOf("-540039728"),
                Integer.valueOf("-986836170"), Integer.valueOf("-1675546118"),
                Integer.valueOf("-1948015872"), Integer.valueOf("-392391576"),
                Integer.valueOf("638256747"), Integer.valueOf("191085745"),
                Integer.valueOf("-97160665"), Integer.valueOf("-1517309270"),
                Integer.valueOf("1375971382"), Integer.valueOf("2133755906"),
                Integer.valueOf("891811204"), Integer.valueOf("-2118700536"),
                Integer.valueOf("2110473668"), Integer.valueOf("-2058625827"),
                Integer.valueOf("861211557"), Integer.valueOf("1573507430"),
                Integer.valueOf("1625767564"), Integer.valueOf("2051434938"),
                Integer.valueOf("-1922104200"), Integer.valueOf("-1271440420"),
                Integer.valueOf("927536580"), Integer.valueOf("1386277121"),
                Integer.valueOf("1184911833"), Integer.valueOf("50057146"),
                Integer.valueOf("-185358571"), Integer.valueOf("-1284027987"),
                Integer.valueOf("-1025692308"), Integer.valueOf("-1093701172"),
                Integer.valueOf("-1116573039"), Integer.valueOf("-1686684117"),
                Integer.valueOf("2068400044"), Integer.valueOf("1009668318"),
                Integer.valueOf("-1847249948"), Integer.valueOf("1670927751"),
                Integer.valueOf("408998980"), Integer.valueOf("-380240510"),
                Integer.valueOf("1439774921"), Integer.valueOf("718930634"),
                Integer.valueOf("-1800042595"), Integer.valueOf("16808464"),
                Integer.valueOf("-1743967552"), Integer.valueOf("1137718941"),
                Integer.valueOf("252008887"), Integer.valueOf("-460347667"),
                Integer.valueOf("-1053605891"), Integer.valueOf("1278046001"),
                Integer.valueOf("947528252"), Integer.valueOf("1842130704"),
                Integer.valueOf("1599145891"), Integer.valueOf("-1341955486"),
                Integer.valueOf("1631478226"), Integer.valueOf("1754478786"),
                Integer.valueOf("-1370798799"), Integer.valueOf("1516348954"),
                Integer.valueOf("730123440"), Integer.valueOf("-1409786204"),
                Integer.valueOf("1491046204"), Integer.valueOf("-1114899725"),
                Integer.valueOf("-656053644"), Integer.valueOf("-453397258"),
                Integer.valueOf("-694901410"), Integer.valueOf("-1804067552"),
                Integer.valueOf("13393714"), Integer.valueOf("-2101289195"),
                Integer.valueOf("176533870"), Integer.valueOf("1339899789"),
                Integer.valueOf("-1511804464"), Integer.valueOf("-1974699707"),
                Integer.valueOf("-1660688399"), Integer.valueOf("-115501757"),
                Integer.valueOf("86156176"), };
    }

    private static int next;

    /**
     * private constructor.
     */
    private RandomGenerator() {
    }

    /**
     * reset method.
     */
    public static void reset() {
        next = 0;
    }

    /**
     * method to get next random number.
     * 
     * @param max
     *            of type int.
     * @return random int.
     */
    public static int nextNumber(final int max) {
        try {
            return (Math.abs(NUMBERS[next]) % max);
        } finally {
            next++;

            if (next >= NUMBERS.length) {
                next = 0;
            }
        }
    }
}
