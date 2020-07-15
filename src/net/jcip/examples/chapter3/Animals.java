package net.jcip.examples.chapter3;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Animals
 * <p/>
 * Thread confinement of local primitive and reference variables
 * 3-9 基本类型的局部变量与引用变量的线程封闭性
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Animals {

    /** 方舟 */
    Ark ark;
    /** 物种 */
    Species species;
    /** 性别 */
    Gender gender;

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        // 候选者
        Animal candidate = null;

        // animals confined to method, don't let them escape!
        animals = new TreeSet<>(new SpeciesGenderComparator());
        animals.add(candidate);
        for(Animal a : animals) {
            if(candidate == null || !candidate.isPotentialMate(a)){
                candidate = a;
            }else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }


    class Animal{
        Species species;
        Gender gender;
        // 潜在的配偶
        public boolean isPotentialMate(Animal other){
            return species == other.species && gender != other.gender;
        }
    }

    enum Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
        IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
        PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
        UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    enum Gender{
        MALE,FEMALE
    }

    class AnimalPair{
        private final Animal one, tow;
        public AnimalPair(Animal one, Animal two){
            this.one = one;
            this.tow = two;
        }
    }

    class SpeciesGenderComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal one, Animal two) {
            int speciesCompare = one.species.compareTo(two.species);
            return (speciesCompare != 0) ? speciesCompare : one.gender.compareTo(two.gender);
        }
    }

    class Ark{
        private final Set<AnimalPair> loadedAnimals = new HashSet<>();
        public void load(AnimalPair pair) {
            loadedAnimals.add(pair);
        }
    }


}
