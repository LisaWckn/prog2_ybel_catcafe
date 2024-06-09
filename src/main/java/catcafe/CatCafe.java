package catcafe;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import tree.Empty;
import tree.Tree;
import tree.TreeVisitor;

/**
 * The CatCafe class manages a collection of FelineOverLord objects using a tree data structure.
 */
public class CatCafe {
    private Tree<FelineOverLord> clowder = new Empty<>();

    /**
     * Adds a new cat to the clowder.
     *
     * @param cat the cat to add, must not be null
     * @throws NullPointerException if the cat is null
     */
    public void addCat(FelineOverLord cat) {
        clowder = clowder.addData(requireNonNull(cat));
    }

    /**
     * Returns the number of cats in the clowder.
     *
     * @return the number of cats
     */
    public long getCatCount() {
        return clowder.size();
    }

    /**
     * Finds a cat by its name.
     *
     * @param name the name of the cat to find, must not be null
     * @return an Optional containing the found cat, or an empty Optional if no cat with the given name is found or the name is null
     */
    public Optional<FelineOverLord> getCatByName(String name) {
        if(name == null){
            return Optional.empty();
        }

        return clowder.stream().filter( c -> c.name().equals(name)).findAny();      
    }

    /**
     * Finds a cat by its weight range.
     *
     * @param minWeight the minimum weight (inclusive), must not be negative
     * @param maxWeight the maximum weight (exclusive), must be greater than or equal to minWeight
     * @return an Optional containing the found cat, or an empty Optional if no cat within the given weight range is found or the weight range is invalid
     */
    public Optional<FelineOverLord> getCatByWeight(int minWeight, int maxWeight) {
        if (minWeight < 0 || maxWeight < minWeight) {
            return Optional.empty();
        }

        return clowder.stream()
            .filter(c -> (c.weight() >= minWeight && c.weight() < maxWeight)).findAny();
    }

     /**
     * Accepts a visitor to process the clowder.
     *
     * @param visitor the visitor to accept
     * @return the result of the visitor's processing as a String
     */
    String accept(TreeVisitor<FelineOverLord> visitor) {
        return clowder.accept(visitor);
    }
}
