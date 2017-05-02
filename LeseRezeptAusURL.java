package de.frank;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Thomas.Darimont
 *
 */
public class LeseRezeptAusURL {

    private static final String ZUTATEN = "Zutaten";

    private static final String ZUBEREITUNG = "Zubereitung";

    private static final String OEKOKISTE = "oekokiste";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        //        String bla =
        //            "https://www.rezeptwelt.de/sonstige-hauptgerichte-rezepte/k%C3%A4se-sahne-nudeln-aus-thermomix-jeden-tag-genie%C3%9Fen/384192";
        //      
        //        String bla = "http://www.oekokiste.de/rezept/show/detail/rezeptid/180.html";
        String bla = "http://www.oekokiste.de/rezept/show/detail/rezeptid/8.html";
        //        String bla = "https://www.rezeptwelt.de/saucendipsbrotaufstriche-rezepte/spinat-k%C3%A4se-so%C3%9Fe/372572";
        //        String bla =
        //            "https://www.rezeptwelt.de/saucendipsbrotaufstriche-rezepte/variation-von-lachs-sahne-so%C3%9Fe/278644";
        LeseRezeptAusURL pps = new LeseRezeptAusURL();
        pps.leseSeite(bla, OEKOKISTE);

    }

    private void leseSeite(String url, String art) throws IOException, MalformedURLException {

        //Brokkoli-Nudelauflauf
        //http://www.oekokiste.de/rezept/show/detail/rezeptid/180.html
        //"Broccoli waschen, die Röschen und Blätter abtrennen und in breite Streifen schneiden. Röschen teilen, dicke Stiele schälen, schräg in 1 cm breite Streifen schneiden. In der Zwischenzeit die Spiralnudeln in kochendem Salzwasser 10 bis 12 Minuten bissfest garen. Abschütten und gut abtropfen lassen. Vom gekochten Schinken den Fettrand entfernen und Schinken in dünne Streifen schneiden; bzw. Räuchertofu würfeln. Eine Auflaufform mit Butter ausfetten. Schinken, bzw. Räuchertofu, Nudeln und Broccoli vorsichtig mischen und in die Form geben. Eier mit der Schlagsahne, Salz, Muskatnuss und Pfeffer verquirlen und über den Auflauf gießen, Emmentaler darüber streuen. Im vorgeheizten Backofen bei 200 ° C circa 25 Minuten goldbraun überbacken."
        //"600g Broccoli, 350 g Spiralnudeln, Salz, 150 g gekochter Schinken, (kann man auch weglassen oder z. B. durch gewürfelten Räuchertofu ersetzen) Butter für die Form, 4 Eier, 200 g Schlagsahne, 2 Messerspitzen ger. Muskatnuss, frisch gemahlener Pfeffer, 100 g ger. Emmentaler."

        //        Chinapfanne mit Sprossen
        //        "http://www.oekokiste.de/rezept/show/detail/rezeptid/8.html";
        //        Die Hühnerbrustfilets in feine Streifen schneiden. Die Sojasauce darübergießen. Das Chinagewürz mit dem Backpulver mischen, über das Fleisch streuen und alles gut mischen. Mit frischem Pfeffer aus der Mühle bestreuen, nochmals mischen und zugedeckt etwa 2 bis 4 Stunden durchziehen lassen. Inzwischen die Zwiebel abziehen und in feine Streifen schneiden. Die Bohnen und die Zuckerschoten putzen, waschen und in etwa 3 cm lange Stücke schneiden. Die Egerlinge abreiben und blättrig schneiden. Den gewaschenen, gut abgetropften Radiccio in feine Streifen schneiden. Die Alfalfa Sprossen ebenfalls gut abtropfen lassen. Das Öl in einem Wok erhitzen, aber nicht zu heiß werden lassen. Das marinierte Fleisch hineingeben und etwa 1 ½ Min. darin unter ständigem Rühren anbraten, dann an den Rand schieben. Nun nach und nach in Abständen von knapp ½ Min. zunächst die Zwiebeln mit dem Ingwer, die Bohnen und Zuckerschoten sowie die Egerlinge in den Wok geben, dabei das Gemüse ständig im Wok bewegen. Zum Schluss das Gemüse mit dem Fleisch mischen, den Fond angießen und abschmecken. Evtl. mit Sojasauce und Chinagewürz nachwürzen. Nun den Radiccio und die Sprossen unterheben und sofort servieren. Dazu Reis oder einfach nur Baguette essen.  
        //        3 bis 4 Hühnerbrustfilets, 6 EL Sojasauce, 1-2 TL Chinagewürz, 1 TL Backpulver, Pfeffer aus der Mühle, 1 Zwiebel, 2 TL feine, frische Ingwerwürfel, 100 g zarte Bohnen, 100 g Zuckerschoten, 12 kleine Egerlinge, ¼ Kopf Radiccio, 1-2 Tassen frische, gut abgespülte Alfalfa Sprossen, 4 bis 6 EL Öl, 1/8 l Geflügelfond

        //TODO Laden, dann in Textdatei speichern. beim nächsten Starten schauen, ob diese Datei schon gefüllt ist. Und dann von dort laden

        //        String bla ="http://www.tutorials.de";
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new URL(url).openStream());

        ArrayList<String> alSteps = new ArrayList();

        //        rezeptweltRezept(sb, scanner, alSteps);
        oekokisteRezept(sb, scanner, alSteps);
        scanner.close();
        for (String string : alSteps) {
            System.out.println(string);
        }
        if (alSteps != null && alSteps.size() > 0) {
            if (alSteps.size() % 2 == 0) {
                int size = alSteps.size();
                for (int i = size - 1; i > ((size / 2) - 1); i--) {
                    System.out.println(i);
                    alSteps.remove(i);

                }

            }

        }
        System.out.println("--------------------");
        for (String string : alSteps) {
            System.out.println(string);
        }
        System.out.println("--------------------");
        System.out.println(sb.toString());
    }

    private void oekokisteRezept(StringBuilder sb, Scanner scanner, ArrayList<String> alSteps) {

        String zubereitung = "";
        String zutaten = "";
        while (scanner.hasNextLine()) {

            String test = scanner.nextLine();
            //            System.out.println(test);
            test = test.trim();

            if (test.contains(ZUBEREITUNG)) {
                String temp = test;
                temp = temp.replace("</h3>", "");
                temp = temp.replace("<p class='value'>", "");
                temp = temp.substring(temp.indexOf(ZUBEREITUNG) + ZUBEREITUNG.length());
                zubereitung = temp.substring(0, temp.indexOf("</p>"));
                System.out.println(zubereitung);

            }

            if (test.contains(ZUTATEN)) {
                String temp = test;
                temp = temp.replace("</h3>", "");
                temp = temp.replace("<p class='value'>", "");
                temp = temp.substring(temp.indexOf(ZUTATEN) + ZUTATEN.length());
                zutaten = temp.substring(0, temp.indexOf("</div>"));
                System.out.println(zutaten);

            }

            //            System.out.println(scanner.nextLine());
        }
    }

    private void rezeptweltRezept(StringBuilder sb, Scanner scanner, ArrayList<String> alSteps) {
        while (scanner.hasNextLine()) {

            String test = scanner.nextLine();
            System.out.println(test);
            test = test.trim();

            if (test.contains("step-content")) {

                test = test.replace("</div>", "");
                test = test.replace("<br />", "");
                test = test.replace("<div class=\"step-content\">", "");
                test = test.replace("<div class=\"step-text-content\">", "");
                test = test.replace("<p class=\"stepNr\">", "");

                test = test.replace("</p> <p>", "");
                test = test.replace("<p>", "");
                test = test.replace("</p>", "");

                test = test.replace("title=", "");
                test = test.replace("alt=", "");

                test = test.replace("\"Mixtopf geschlossen\"", "");
                test = test.replace("\"Linkslauf\"", "");
                test = test.replace("\"Sanftrührstufe\"", "");

                test = test.replace(
                    "http://de.cdn.community.thermomix.com/sites/all/modules/contrib/smileys/packs/thermomix/soft.png",
                    "\\sanftruehr");
                test = test.replace(
                    "https://de.cdn.community.thermomix.com/sites/all/modules/contrib/smileys/packs/thermomix/soft.png",
                    "\\sanftruehr");

                test = test.replace(
                    "\"http://de.cdn.community.thermomix.com/sites/all/modules/contrib/smileys/packs/thermomix/counterclock.png\"",
                    "\\linkslauf");

                test = test.replace(
                    "\"https://de.cdn.community.thermomix.com/sites/all/modules/contrib/smileys/packs/thermomix/counterclock.png\"",
                    "\\linkslauf");
                test = test.replace("<img src=", "");
                test = test.replace("/>", "");
                test = test.replace(
                    "\"https://de.cdn.community.thermomix.com/sites/all/modules/contrib/smileys/packs/thermomix/mixingbowl.png\"",
                    "\\mixtopf");

                test = "\\step" + test.substring(test.indexOf(".") + 1);

                test = test.replace("&nbsp;", " ");

                test = test.replace(
                    "\"http://de.cdn.community.thermomix.com/sites/all/modules/contrib/smileys/packs/thermomix/mixingbowl.png\"",
                    "\\mixtopf");

                test = test.replace("</span>", "");
                test = test.replace("<span>", "");

                test = test.replace("<strong>", "");
                test = test.replace("</strong>", "");
                //                test = test.replace("<p class=\"stepNr\">", "");
                test = test.replace("   ", " ");
                test = test.replace("  ", " ");

                alSteps.add(test);
                //                sb.append("\n");

                //                \step Eier trennen

            }
            if (test.contains("groupheader")) {
                sb.append(test);
                sb.append("\n");
            }

            if (test.contains("itemprop=\"name")) {
                sb.append(test);
                sb.append("\n");
            }

            if (test.contains("recipemainpicture[0][file]")) {
                sb.append(test);
                sb.append("\n");
            }

            if (test.contains("recipemainpicture[0][title]")) {
                sb.append(test);
                sb.append("\n");
            }

            if (test.contains("groupheader")) {

            }

            //            System.out.println(scanner.nextLine());
        }
    }

}