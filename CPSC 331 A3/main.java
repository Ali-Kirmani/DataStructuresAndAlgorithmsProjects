/*Ali Kirmani 30115539 and Ibrahim Ahmed 30125006 
 * main class for part 2 to generate the trees and call the methods
*/

import java.util.Random;

public class main {
    public static void main(String[] args) {
        int[] treeSizes = {500, 1000, 5000};

        for (int size : treeSizes) {
            int numTrees = 100;
            int totalHeight = 0;
            int completeTrees = 0;
            int perfectTrees = 0;
            int treesWithDuplicates = 0;

            for (int i = 0; i < numTrees; i++) {
                BST<Integer> tree = generateRandomTree(size);
                int height = tree.height();

                totalHeight = totalHeight + height;
                if (tree.isComplete()) {
                    completeTrees++;
                }
                if (tree.isPerfect()) {
                    perfectTrees++;
                }
                if (tree.hasDoubles()) {
                    treesWithDuplicates++;
                }
            }

            double averageHeight = (double) totalHeight / numTrees;

            System.out.println("\nTree Size: " + size);
            System.out.println("Average Height: " + averageHeight);
            System.out.println("Number of Complete Trees: " + completeTrees);
            System.out.println("Number of Perfect Trees: " + perfectTrees);
            System.out.println("Number of Trees with Duplicates: " + treesWithDuplicates);
        
        }
    }

    public static BST<Integer> generateRandomTree(int size) {
        BST<Integer> tree = new BST<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int value = random.nextInt(size);
            tree.add(value);
        }

        return tree;
    }
}