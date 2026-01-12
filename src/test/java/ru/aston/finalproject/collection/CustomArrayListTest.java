package ru.aston.finalproject.collection;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayListTest {

    @Nested
    class ConstructorTests {

        @Test
        void givenConstructorWithInitialCapacity_whenCreateListWithPositiveCapacity_thenListCreatedWithSpecifiedCapacity() {
            CustomArrayList<Integer> list = new CustomArrayList<>(50);
            assertEquals(0, list.size());
            assertTrue(list.capacity() >= 50);
        }

        @Test
        void givenConstructorWithZeroCapacity_whenCreateList_thenListCreatedWithEmptyElementArray() {
            CustomArrayList<Integer> list = new CustomArrayList<>(0);
            assertEquals(0, list.size());
            assertEquals(0, list.capacity());
            assertTrue(list.isEmpty());
        }

        @Test
        void givenConstructorWithNegativeCapacity_whenCreateList_thenThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<>(-1));
        }

        @Test
        void givenCollectionOfElements_whenCreateListWithCollectionConstructor_thenListCreatedWithCollectionElements() {
            List<Integer> sourceList = Arrays.asList(1, 2, 3, 4, 5);
            CustomArrayList<Integer> list = new CustomArrayList<>(sourceList);
            assertEquals(5, list.size());
            assertFalse(list.isEmpty());
            assertTrue(list.containsAll(sourceList));
        }

        @Test
        void givenNullCollection_whenCreateListWithCollectionConstructor_thenThrowNullPointerException() {
            assertThrows(NullPointerException.class, () -> new CustomArrayList<>(null));
        }

        @Test
        void givenEmptyCollection_whenCreateListWithCollectionConstructor_thenEmptyListCreated() {
            List<Integer> emptyList = Collections.emptyList();
            CustomArrayList<Integer> list = new CustomArrayList<>(emptyList);
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        void givenLargeCollection_whenCreateListWithCollectionConstructor_thenCapacitySetCorrectly() {
            List<Integer> largeList = IntStream.range(0, 1000)
                    .boxed()
                    .collect(Collectors.toList());
            CustomArrayList<Integer> list = new CustomArrayList<>(largeList);
            assertEquals(1000, list.size());
            assertTrue(list.capacity() >= 1000);
        }
    }

    @Nested
    class AddElementsTests {

        @Test
        void givenEmptyList_whenAddElement_thenElementAddedAndSizeIncreased() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            boolean result = list.add(42);
            assertTrue(result);
            assertEquals(1, list.size());
            assertEquals(42, list.get(0));
            assertFalse(list.isEmpty());
        }

        @Test
        void givenList_whenAddNull_thenNullAddedAsElement() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add(null);
            assertEquals(1, list.size());
            assertNull(list.get(0));
        }

        @Test
        void givenList_whenAddElementAtIndexZero_thenElementInsertedAtBeginning() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(0, 0);
            assertEquals(4, list.size());
            assertEquals(0, list.get(0));
            assertEquals(1, list.get(1));
            assertEquals(2, list.get(2));
            assertEquals(3, list.get(3));
        }

        @Test
        void givenList_whenAddElementAtMiddleIndex_thenElementInsertedInMiddle() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(4);
            list.add(5);
            list.add(2, 3);
            assertEquals(5, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
            assertEquals(4, list.get(3));
            assertEquals(5, list.get(4));
        }

        @Test
        void givenList_whenAddElementAtLastIndex_thenElementAddedAtEnd() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(3, 4);
            assertEquals(4, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
            assertEquals(4, list.get(3));
        }

        @Test
        void givenList_whenAddAllCollection_thenAllCollectionElementsAdded() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            List<Integer> toAdd = Arrays.asList(3, 4, 5);
            boolean result = list.addAll(toAdd);
            assertTrue(result);
            assertEquals(5, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
            assertEquals(4, list.get(3));
            assertEquals(5, list.get(4));
        }

        @Test
        void givenList_whenAddAllEmptyCollection_thenReturnFalseAndListUnchanged() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            List<Integer> emptyList = Collections.emptyList();
            boolean result = list.addAll(emptyList);
            assertFalse(result);
            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
        }

        @Test
        void givenList_whenAddAllNullCollection_thenThrowNullPointerException() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            assertThrows(NullPointerException.class, () -> list.addAll(null));
        }

        @Test
        void givenList_whenAddAllCollectionAtIndex_thenElementsInsertedAtSpecifiedPosition() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(5);
            List<Integer> toInsert = Arrays.asList(2, 3, 4);
            boolean result = list.addAll(1, toInsert);
            assertTrue(result);
            assertEquals(5, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
            assertEquals(4, list.get(3));
            assertEquals(5, list.get(4));
        }

        @Test
        void givenList_whenAddAllCustomArrayListCollection_thenElementsCopiedEfficiently() {
            CustomArrayList<Integer> list1 = new CustomArrayList<>();
            list1.add(1);
            list1.add(2);
            CustomArrayList<Integer> list2 = new CustomArrayList<>();
            list2.add(3);
            list2.add(4);
            boolean result = list1.addAll(list2);
            assertTrue(result);
            assertEquals(4, list1.size());
            assertEquals(1, list1.get(0));
            assertEquals(2, list1.get(1));
            assertEquals(3, list1.get(2));
            assertEquals(4, list1.get(3));
        }
    }

    @Nested
    class RemoveElementsTests {

        @Test
        void givenList_whenRemoveElementByIndex_thenElementRemovedAndSizeDecreased() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Integer removed = list.remove(1);
            assertEquals(2, removed);
            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(3, list.get(1));
        }

        @Test
        void givenList_whenRemoveFirstElementByIndex_thenFirstElementRemoved() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Integer removed = list.remove(0);
            assertEquals(1, removed);
            assertEquals(2, list.size());
            assertEquals(2, list.get(0));
            assertEquals(3, list.get(1));
        }

        @Test
        void givenList_whenRemoveLastElementByIndex_thenLastElementRemoved() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Integer removed = list.remove(2);
            assertEquals(3, removed);
            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
        }

        @Test
        void givenList_whenRemoveElementByValue_thenElementRemovedIfFound() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            boolean result = list.remove("banana");
            assertTrue(result);
            assertEquals(2, list.size());
            assertEquals("apple", list.get(0));
            assertEquals("cherry", list.get(1));
        }

        @Test
        void givenList_whenRemoveNonExistentElementByValue_thenReturnFalseAndListUnchanged() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            boolean result = list.remove("cherry");
            assertFalse(result);
            assertEquals(2, list.size());
            assertEquals("apple", list.get(0));
            assertEquals("banana", list.get(1));
        }

        @Test
        void givenList_whenRemoveNullByValue_thenNullRemovedIfFound() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add(null);
            list.add("banana");
            boolean result = list.remove(null);
            assertTrue(result);
            assertEquals(2, list.size());
            assertEquals("apple", list.get(0));
            assertEquals("banana", list.get(1));
        }

        @Test
        void givenList_whenClearList_thenListBecomesEmpty() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
            assertTrue(list.capacity() > 0);
        }

        @Test
        void givenList_whenRemoveAllElements_thenSpecifiedElementsRemoved() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            List<Integer> toRemove = Arrays.asList(2, 4);
            boolean result = list.removeAll(toRemove);
            assertTrue(result);
            assertEquals(3, list.size());
            assertEquals(1, list.get(0));
            assertEquals(3, list.get(1));
            assertEquals(5, list.get(2));
        }

        @Test
        void givenList_whenRemoveAllEmptyCollection_thenReturnFalseAndListUnchanged() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            List<Integer> emptyList = Collections.emptyList();
            boolean result = list.removeAll(emptyList);
            assertFalse(result);
            assertEquals(2, list.size());
        }

        @Test
        void givenList_whenRemoveAllNullCollection_thenThrowNullPointerException() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            assertThrows(NullPointerException.class, () -> list.removeAll(null));
        }

        @Test
        void givenList_whenRetainAllElements_thenOnlySpecifiedElementsRetained() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            List<Integer> toRetain = Arrays.asList(2, 3, 4);
            boolean result = list.retainAll(toRetain);
            assertTrue(result);
            assertEquals(3, list.size());
            assertEquals(2, list.get(0));
            assertEquals(3, list.get(1));
            assertEquals(4, list.get(2));
        }

        @Test
        void givenList_whenRetainAllSameCollection_thenReturnFalseAndListUnchanged() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            List<Integer> sameList = new ArrayList<>(list);
            boolean result = list.retainAll(sameList);
            assertFalse(result);
            assertEquals(3, list.size());
        }
    }

    @Nested
    class GetAndSetElementsTests {

        @Test
        void givenList_whenGetElementByValidIndex_thenElementReturned() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            String element = list.get(1);
            assertEquals("banana", element);
        }

        @Test
        void givenList_whenSetElementByIndex_thenElementChangedAndOldValueReturned() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            String oldValue = list.set(1, "blueberry");
            assertEquals("banana", oldValue);
            assertEquals(3, list.size());
            assertEquals("apple", list.get(0));
            assertEquals("blueberry", list.get(1));
            assertEquals("cherry", list.get(2));
        }

        @Test
        void givenList_whenSetElementByInvalidIndex_thenThrowIndexOutOfBoundsException() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "banana"));
        }

        @Test
        void givenList_whenCheckContainsElement_thenReturnTrueIfElementExists() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add(null);
            assertTrue(list.contains("apple"));
            assertTrue(list.contains(null));
            assertFalse(list.contains("cherry"));
        }

        @Test
        void givenList_whenFindIndexOfElement_thenReturnFirstIndex() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("apple");
            list.add("cherry");
            int index = list.indexOf("apple");
            assertEquals(0, index);
        }

        @Test
        void givenList_whenElementNotFoundWithIndexOf_thenReturnMinusOne() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            int index = list.indexOf("cherry");
            assertEquals(-1, index);
        }

        @Test
        void givenList_whenFindLastIndexOfElement_thenReturnLastIndex() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("apple");
            list.add("cherry");
            int lastIndex = list.lastIndexOf("apple");
            assertEquals(2, lastIndex);
        }

        @Test
        void givenList_whenCheckContainsAllWithSubset_thenReturnTrue() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            List<Integer> subset = Arrays.asList(2, 3, 4);
            boolean result = list.containsAll(subset);
            assertTrue(result);
        }

        @Test
        void givenList_whenCheckContainsAllWithNonExistentElements_thenReturnFalse() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            List<Integer> nonSubset = Arrays.asList(2, 3, 4, 5);
            boolean result = list.containsAll(nonSubset);
            assertFalse(result);
        }
    }

    @Nested
    class IteratorTests {

        @Test
        void givenList_whenIterateWithIterator_thenAllElementsTraversedInCorrectOrder() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            Iterator<String> iterator = list.iterator();
            List<String> result = new ArrayList<>();
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }
            assertEquals(3, result.size());
            assertEquals("apple", result.get(0));
            assertEquals("banana", result.get(1));
            assertEquals("cherry", result.get(2));
        }

        @Test
        void givenList_whenUseForEachLoop_thenAllElementsProcessed() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            List<Integer> result = new ArrayList<>();
            for (Integer num : list) {
                result.add(num);
            }
            assertEquals(3, result.size());
            assertEquals(1, result.get(0));
            assertEquals(2, result.get(1));
            assertEquals(3, result.get(2));
        }

        @Test
        void givenList_whenRemoveElementViaIterator_thenElementRemovedFromList() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            Iterator<String> iterator = list.iterator();
            iterator.next();
            iterator.next();
            iterator.remove();
            assertEquals(2, list.size());
            assertEquals("apple", list.get(0));
            assertEquals("cherry", list.get(1));
        }

        @Test
        void givenList_whenCallIteratorRemoveWithoutNext_thenThrowIllegalStateException() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            Iterator<String> iterator = list.iterator();
            assertThrows(IllegalStateException.class, iterator::remove);
        }

        @Test
        void givenList_whenCallIteratorNextAfterLastElement_thenThrowNoSuchElementException() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            Iterator<String> iterator = list.iterator();
            iterator.next();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void givenList_whenModifyListDuringIteration_thenThrowConcurrentModificationException() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Iterator<Integer> iterator = list.iterator();
            iterator.next();
            list.add(4);
            assertThrows(ConcurrentModificationException.class, iterator::next);
        }

        @Test
        void givenList_whenModifyListViaAnotherIterator_thenThrowConcurrentModificationException() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            Iterator<Integer> iterator1 = list.iterator();
            Iterator<Integer> iterator2 = list.iterator();
            iterator1.next();
            iterator1.remove();
            assertThrows(ConcurrentModificationException.class, iterator2::next);
        }
    }

    @Nested
    class CapacityAndPerformanceTests {

        @Test
        void givenEmptyList_whenAddManyElements_thenCapacityAutomaticallyIncreases() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            int initialCapacity = list.capacity();
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
            assertEquals(1000, list.size());
            assertTrue(list.capacity() >= 1000);
            assertTrue(list.capacity() > initialCapacity);
        }

        @Test
        void givenList_whenTrimToSize_thenCapacityReducedToListSize() {
            CustomArrayList<Integer> list = new CustomArrayList<>(100);
            list.add(1);
            list.add(2);
            list.add(3);
            int capacityBefore = list.capacity();
            list.trimToSize();
            assertEquals(3, list.size());
            assertEquals(3, list.capacity());
            assertTrue(capacityBefore > list.capacity());
        }

        @Test
        void givenEmptyList_whenTrimToSize_thenCapacityBecomesZero() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.trimToSize();
            assertEquals(0, list.size());
            assertEquals(0, list.capacity());
        }

        @Test
        void givenList_whenGetCapacity_thenReturnCurrentInternalArrayCapacity() {
            CustomArrayList<Integer> list = new CustomArrayList<>(50);
            int capacity = list.capacity();
            assertTrue(capacity >= 50);
        }

        @Test
        void givenList_whenCheckGrowthStrategyInfo_thenReturnGrowthStrategyInformation() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            String info = list.getGrowthStrategyInfo();
            assertNotNull(info);
            assertTrue(info.contains("Current capacity"));
            assertTrue(info.contains("growth"));
        }

        @Test
        void givenListWithCapacityBelowThreshold_whenAddElements_thenFastGrowthStrategyUsed() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            int initialCapacity = list.capacity();
            for (int i = 0; i < initialCapacity + 1; i++) {
                list.add(i);
            }
            String info = list.getGrowthStrategyInfo();
            if (initialCapacity < 1000) {
                assertTrue(info.contains("fast growth"));
            }
        }
    }

    @Nested
    class ConversionAndCloningTests {

        @Test
        void givenList_whenConvertToArray_thenReturnArrayWithListElements() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            Object[] array = list.toArray();
            assertEquals(3, array.length);
            assertEquals("apple", array[0]);
            assertEquals("banana", array[1]);
            assertEquals("cherry", array[2]);
        }

        @Test
        void givenEmptyList_whenConvertToArray_thenReturnEmptyArray() {
            CustomArrayList<String> list = new CustomArrayList<>();
            Object[] array = list.toArray();
            assertEquals(0, array.length);
        }

        @Test
        void givenList_whenCloneList_thenNewIndependentCopyCreated() {
            CustomArrayList<String> original = new CustomArrayList<>();
            original.add("apple");
            original.add("banana");
            original.add("cherry");
            @SuppressWarnings("unchecked")
            CustomArrayList<String> clone = (CustomArrayList<String>) original.clone();
            assertNotSame(original, clone);
            assertEquals(original.size(), clone.size());
            assertEquals(original.get(0), clone.get(0));
            assertEquals(original.get(1), clone.get(1));
            assertEquals(original.get(2), clone.get(2));
            original.add("date");
            assertEquals(4, original.size());
            assertEquals(3, clone.size());
        }

        @Test
        void givenListWithNullElements_whenClone_thenNullElementsAlsoCopied() {
            CustomArrayList<String> original = new CustomArrayList<>();
            original.add("apple");
            original.add(null);
            original.add("cherry");
            @SuppressWarnings("unchecked")
            CustomArrayList<String> clone = (CustomArrayList<String>) original.clone();
            assertEquals(3, clone.size());
            assertEquals("apple", clone.get(0));
            assertNull(clone.get(1));
            assertEquals("cherry", clone.get(2));
        }
    }

    @Nested
    class ToStringAndOtherMethodsTests {

        @Test
        void givenEmptyList_whenToString_thenReturnEmptyBrackets() {
            CustomArrayList<String> list = new CustomArrayList<>();
            String result = list.toString();
            assertEquals("[]", result);
        }

        @Test
        void givenListWithOneElement_whenToString_thenReturnElementInBrackets() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            String result = list.toString();
            assertEquals("[apple]", result);
        }

        @Test
        void givenListWithMultipleElements_whenToString_thenReturnElementsInBracketsSeparatedByCommas() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add("banana");
            list.add("cherry");
            String result = list.toString();
            assertEquals("[apple, banana, cherry]", result);
        }

        @Test
        void givenListWithNullElements_whenToString_thenNullDisplayedAsNull() {
            CustomArrayList<String> list = new CustomArrayList<>();
            list.add("apple");
            list.add(null);
            list.add("cherry");
            String result = list.toString();
            assertEquals("[apple, null, cherry]", result);
        }

        @Test
        void givenTwoIdenticalLists_whenCompareWithEquals_thenReturnTrue() {
            CustomArrayList<String> list1 = new CustomArrayList<>();
            list1.add("apple");
            list1.add("banana");
            CustomArrayList<String> list2 = new CustomArrayList<>();
            list2.add("apple");
            list2.add("banana");
            assertEquals(list1, list2);
        }

        @Test
        void givenTwoDifferentLists_whenCompareWithEquals_thenReturnFalse() {
            CustomArrayList<String> list1 = new CustomArrayList<>();
            list1.add("apple");
            list1.add("banana");
            CustomArrayList<String> list2 = new CustomArrayList<>();
            list2.add("apple");
            list2.add("cherry");
            assertNotEquals(list1, list2);
        }

        @Test
        void givenList_whenCreateSubList_thenReturnCorrectSubList() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            List<Integer> subList = list.subList(1, 4);
            assertEquals(3, subList.size());
            assertEquals(2, subList.get(0));
            assertEquals(3, subList.get(1));
            assertEquals(4, subList.get(2));
        }

        @Test
        void givenList_whenCreateSubListWithInvalidIndices_thenThrowIndexOutOfBoundsException() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            list.add(1);
            list.add(2);
            assertThrows(IndexOutOfBoundsException.class, () -> list.subList(1, 3));
            assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 1));
        }
    }

    @Nested
    class EdgeCasesTests {

        @Test
        void givenListWithZeroCapacity_whenAddElement_thenCapacityIncreases() {
            CustomArrayList<Integer> list = new CustomArrayList<>(0);
            assertEquals(0, list.capacity());
            list.add(1);
            assertEquals(1, list.size());
            assertTrue(list.capacity() > 0);
        }

        @Test
        void givenList_whenAddMaximumNumberOfElements_thenListWorksCorrectly() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            int largeNumber = 10000;
            for (int i = 0; i < largeNumber; i++) {
                list.add(i);
            }
            assertEquals(largeNumber, list.size());
            assertEquals(999, list.get(999));
            assertEquals(9999, list.get(9999));
        }

        @Test
        void givenList_whenAddAndRemoveManyElements_thenInternalArrayDoesNotLeakMemory() {
            CustomArrayList<Integer> list = new CustomArrayList<>();
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }

            int capacityAfterAdd = list.capacity();
            for (int i = 0; i < 900; i++) {
                list.remove(0);
            }
            assertEquals(100, list.size());
            assertEquals(capacityAfterAdd, list.capacity());
            list.trimToSize();
            assertEquals(100, list.capacity());
        }

        @Test
        void givenList_whenWorkWithLargeObjects_thenMemoryManagedCorrectly() {
            CustomArrayList<StringBuilder> list = new CustomArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(new StringBuilder("LargeObject").append(i));
            }
            list.clear();
            assertEquals(0, list.size());
            for (int i = 0; i < list.capacity(); i++) {
                assertTrue(list.isEmpty());
            }
        }
    }
}