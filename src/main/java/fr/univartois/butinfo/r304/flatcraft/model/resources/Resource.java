/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.resources;

import java.util.Objects;

import fr.univartois.butinfo.r304.flatcraft.model.resources.state.ResourceState;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * Une ressource est un élément de la carte avec lequel le joueur peut interagir.
 * Il peut soit l'extraire, soit la laisser sur place.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Resource {

    /**
     * Le nom unique identifiant le type de cette ressource.
     */
    private final String name;

    /**
     * Le type d'outils nécessaire pour extraire cette ressource de la carte.
     */
    private final ToolType toolType;

    /**
     * La dureté de cette ressource.
     * Il s'agit du nombre de coups devant être appliqués avec un outil pour extraire
     * cette ressource depuis la map.
     */
    private int hardness;

    /**
     * L'état de cette ressource.
     */
    private ResourceState state;

    /**
     * Crée une nouvelle instance de Resource.
     *
     * @param name Le nom unique identifiant le type de la ressource.
     * @param toolType Le type d'outils nécessaire pour extraire la ressource de la carte.
     * @param hardness La dureté initiale de la ressource.
     *
     * @throws IllegalArgumentException Si la valeur de {@code hardness} est négative.
     */
    public Resource(String name, ToolType toolType, int hardness, ResourceState state) {
        if (hardness < 0) {
            throw new IllegalArgumentException("Resource hardness should be non-negative!");
        }

        this.name = name;
        this.toolType = toolType;
        this.hardness = hardness;
        this.state = state;
    }

    /**
     * Donne le nom unique identifiant le type de cette ressource.
     *
     * @return Le nom de cette ressource.
     */
    public String getName() {
        return name;
    }

    /**
     * Donne le sprite représentant cette ressource.
     *
     * @return Le sprite représentant cette ressource.
     */
    public Sprite getSprite() {
        return state.getSprite();
    }

    /**
     * Donne le type d'outils nécessaire pour extraire cette ressource de la carte.
     *
     * @return Le type d'outils à utiliser.
     */
    public ToolType getToolType() {
        return toolType;
    }

    /**
     * Donne la dureté de cette ressource.
     * Il s'agit du nombre de coups devant être appliqués avec un outil pour extraire
     * cette ressource depuis la map.
     *
     * @return La dureté de cette ressource.
     */
    public int getHardness() {
        return hardness;
    }

    /**
     * Donne un coup sur cette ressource pour l'extraire de la carte.
     * Cela réduit sa dureté.
     *
     * @throws IllegalStateException Si la dureté de la ressource est déjà égale à
     *         {@code 0}.
     */
    public void dig() {
        if (hardness <= 0) {
            throw new IllegalStateException("Cannot dig resource with 0 hardness!");
        }
        hardness--;
    }

    /**
     * Donne la ressource obtenue lorsque cette ressource est extraite de la carte.
     * Par défaut, la ressource obtenue ne change pas.
     *
     * @return La ressource obtenue après son extraction.
     */
    public Resource digBlock() {
        state = state.nextState();
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Resource resource) {
            return name.equals(resource.name);
        }
        return false;
    }
}
