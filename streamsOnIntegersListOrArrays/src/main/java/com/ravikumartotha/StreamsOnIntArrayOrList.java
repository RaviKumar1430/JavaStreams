package com.ravikumartotha;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsOnIntArrayOrList {
    public static void main(String[] args) {
        List<Integer> uniqueNaturalNumberList = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> duplicatesNaturalNumbersList = List.of(1,1,2,1,2,3,4,3,5,6,5,5,6,7,8,7,8,9,10,10);
        List<Integer> randomnNumbersList = List.of(22, 33, 45, 76, 67, 32, 45, 67, 89, 41, 45);

        int[] arr = {3,6,7,8,1,4,4,6,7,3,9};

        //1. Convert a List of Integers to a List of Strings
        List<String> stringList = uniqueNaturalNumberList.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        System.out.println("String numbers list: " + stringList);

        //2. Group Numbers by Even or Odd
        Map<String, List<Integer>> grouped = uniqueNaturalNumberList.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));

        grouped.entrySet().forEach(System.out::println);

        //3. Find the Second-Largest Element in a list or array
        Optional<Integer> secondLargest = duplicatesNaturalNumbersList.stream()
                                    .distinct()  // Remove duplicates
                                    .sorted(Comparator.reverseOrder())
                                    .skip(1)  // Skip the largest element
                                    .findFirst();  // Get the second largest
        secondLargest.ifPresentOrElse(
                value -> System.out.println("Second largest element: " + value),
                () -> System.out.println("No second largest element.")
        );

        //4. Find the Common Elements Between Two Lists or arrays
        List<Integer> commonElements = uniqueNaturalNumberList.stream()
                .filter(n -> duplicatesNaturalNumbersList.stream().anyMatch(x -> Objects.equals(x, n)))
                .collect(Collectors.toList());
        System.out.println("Common elements: " + commonElements);

        List<Integer> intersection = uniqueNaturalNumberList.stream()
                .filter(duplicatesNaturalNumbersList::contains)
                .collect(Collectors.toList());
        System.out.println("Intersection: " + intersection);

        //5. Merge Two Lists and Remove Duplicates
        List<Integer> merged = Stream.concat(randomnNumbersList.stream(), duplicatesNaturalNumbersList.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Merged list: "+merged);

        //6. Find the Pair with the Maximum Sum
        List<int[]> pairs = Arrays.stream(arr).boxed()
                .flatMap(a -> Arrays.stream(arr)
                        .filter(b -> a != b)
                        .mapToObj(b -> new int[]{a, b}))
                .collect(Collectors.toList());
        int[] maxPair = pairs.stream()
                .max(Comparator.comparingInt(pair -> pair[0] + pair[1]))
                .orElse(new int[]{0, 0});

        System.out.println("Pair with max sum: (" + maxPair[0] + ", " + maxPair[1] + ")");

        //7. Find the Pair with the Maximum Product
        List<int[]> pairs1 = Arrays.stream(arr).boxed()
                .flatMap(a -> Arrays.stream(arr)
                        .filter(b -> a != b)
                        .mapToObj(b -> new int[]{a, b}))
                .collect(Collectors.toList());
        int[] maxPair1 = pairs1.stream()
                .max(Comparator.comparingInt(pair -> pair[0] * pair[1]))
                .orElse(new int[]{0, 0});

        System.out.println("Pair with max product: (" + maxPair1[0] + ", " + maxPair1[1] + ")");
        System.out.println("Max product: "+maxPair1[0]*maxPair1[1]);

        //8. Find the Most Frequent Number
        Optional<Integer> mostFrequent = randomnNumbersList.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey);
        System.out.print("Most Frequent number: ");
        mostFrequent.ifPresentOrElse(System.out::println, () -> System.out.println("No most frequent number"));

        //9. Find the First Non-Repeating Element
        Map<Integer, Long> frequencyMap = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, LinkedHashMap::new,Collectors.counting()));

        Integer firstNonRepeating = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First non-repeating element: " + firstNonRepeating);

        //10. Generate a List of Fibonacci Numbers
        int n = 10;
        List<Integer> fibonacciNumbers = Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .limit(n)
                .map(f -> f[0])
                .collect(Collectors.toList());
        System.out.println("Fibonacci numbers: "+fibonacciNumbers);


    }
}