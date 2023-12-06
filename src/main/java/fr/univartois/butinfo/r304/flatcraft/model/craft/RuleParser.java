/**
 * Ce logiciel est distribué à des fins éducatives.
 * <p>
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 * <p>
 * (c) 2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.craft;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@link RuleParser} permet de lire un fichier de règles de craft.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */

public final class RuleParser {

    /**
     * Le nom du fichier depuis lequel les règles doivent être lues.
     */

    private final String fileName;

    private static RuleParser instanceCraft = new RuleParser("craftrules.txt");

    private static RuleParser instanceFurnace = new RuleParser("furnacerules.txt");

    private static RuleBuilder builder = new RuleBuilder();

    private static List<Rule> rules = new ArrayList<>();

    /**
     * Crée une nouvelle instance de RuleParser.
     *
     * @param fileName Le nom du fichier depuis lequel les règles doivent être lues.
     */

    private RuleParser(String fileName) {

        this.fileName = fileName;

    }

    /**
     * Lit les règles depuis le fichier associé.
     *
     * @throws IOException Si une erreur se produit au cours de la lecture.
     */

    public void parse() throws IOException {

        try (BufferedReader reader = new BufferedReader(

                new InputStreamReader(getClass().getResourceAsStream(fileName)))) {

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] splitted = line.split("=");

                String[] result = splitted[1].split(" ");

                if (result.length == 1) {

                    addRule(splitted[0], splitted[1], 1);

                } else {

                    addRule(splitted[0], splitted[1], Integer.parseInt(result[1]));

                }

            }

        }

    }

    /**
     * Ajoute une règle ayant été lue.
     *
     * @param rule     La règle à ajouter.
     * @param product  Le résultat de l'application de la règle.
     * @param quantity La quantité obtenue pour la ressource produite.
     */

    private void addRule(String rule, Resource product, int quantity) {
        Rule rule1 = builder.getRule();
        rule1.setRule(rule);
        rule1.setProduct(product);
        rule1.setQuantity(quantity);
        rules.add(rule1);
    }

    public static RuleParser getInstanceCraft() {
        return instanceCraft;
    }

    public static RuleParser getInstanceFurnace() {
        return instanceFurnace;
    }

    public static List<Rule> getRules() {
        return rules;
    }
}

