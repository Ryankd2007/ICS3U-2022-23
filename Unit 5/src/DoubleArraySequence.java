/******************************************************************************
 * A DoubleArraySequence is a collection of double numbers.
 * The sequence can have a special "current element," which is specified and
 * accessed through four methods that are not available in the sequence class
 * (start, getCurrent, advance and isCurrent).
 *
 * @note
 *       (1) The capacity of one a sequence can change after it's created, but
 *       the maximum capacity is limited by the amount of free memory on the
 *       machine. The constructor, addAfter,
 *       addBefore, clone,
 *       and concatenation will result in an
 *       OutOfMemoryError when free memory is exhausted.
 *       <p>
 *       (2) A sequence's capacity cannot exceed the maximum integer
 *       2,147,483,647
 *       (Integer.MAX_VALUE). Any attempt to create a larger capacity
 *       results in a failure due to an arithmetic overflow.
 *
 * @note
 *       This file contains only blank implementations ("stubs")
 *       because this is a Programming Project for my students.
 *
 *
 * 
 * @version
 *          January 26, 2023
 ******************************************************************************/
public class DoubleArraySequence implements Cloneable {
   private static final int DEFAULT_CAPACITY = 10;
   // Invariant of the DoubleArraySequence class:
   // 1. The number of elements in the seqeunces is in the instance variable
   // manyItems.
   // 2. For an empty sequence (with no elements), we do not care what is
   // stored in any of data; for a non-empty sequence, the elements of the
   // sequence are stored in data[0] through data[manyItems-1], and we
   // don't care what's in the rest of data.
   // 3. If there is a current element, then it lies in data[currentIndex];
   // if there is no current element, then currentIndex equals manyItems.
   private double[] data;
   private int manyItems;
   private int currentIndex;

   /**
    * Initialize an empty sequence with an initial capacity of 10. Note that
    * the addAfter and addBefore methods work
    * efficiently (without needing more memory) until this capacity is reached.
    * 
    * @param - none
    * @postcondition
    *                This sequence is empty and has an initial capacity of 10.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for:
    *                             new double[10].
    **/
   // Created DoubleArraySequence
   public DoubleArraySequence() {
      data = new double[DEFAULT_CAPACITY]; //creates main array
      manyItems = 0;
      currentIndex = 0;
   }

   /**
    * Initialize an empty sequence with a specified initial capacity. Note that
    * the addAfter and addBefore methods work
    * efficiently (without needing more memory) until this capacity is reached.
    * 
    * @param initialCapacity
    *                        the initial capacity of this sequence
    * @precondition
    *               initialCapacity is non-negative.
    * @postcondition
    *                This sequence is empty and has the given initial capacity.
    * @exception IllegalArgumentException
    *                                     Indicates that initialCapacity is
    *                                     negative.
    * @exception OutOfMemoryError
    *                                     Indicates insufficient memory for:
    *                                     new double[initialCapacity].
    **/
   // Get empty sequence with a specified Capacity
   public DoubleArraySequence(int initialCapacity) {
      if (initialCapacity < 0)
         throw new IllegalArgumentException("Capacity must be positive"); //ensures that the capacity isnt negative

      data = new double[initialCapacity]; //makes data the size of initial capacity
      manyItems = 0;
      currentIndex = 0;
   }

   /**
    * Add a new element to this sequence, after the current element.
    * If the new element would take this sequence beyond its current capacity,
    * then the capacity is increased before adding the new element.
    * 
    * @param d
    *          the new element that is being added
    * @postcondition
    *                A new copy of the element has been added to this sequence. If
    *                there was
    *                a current element, then the new element is placed after the
    *                current
    *                element. If there was no current element, then the new element
    *                is placed
    *                at the end of the sequence. In all cases, the new element
    *                becomes the
    *                new current element of this sequence.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for increasing the
    *                             sequence's capacity.
    * @note
    *       An attempt to increase the capacity beyond
    *       Integer.MAX_VALUE will cause the sequence to fail with an
    *       arithmetic overflow.
    **/
   public void addAfter(double d) {
      if(manyItems == data.length){ //if the amount of items in the array is the same length as the array
         int newCapacity = data.length*2; //make a variable that is double the length of the data array
         double[] temp = new double[newCapacity]; //make a temp array thats double the length of the data array
         for(int i = 0; i < data.length; i++){
            temp[i] = data[i]; //creates a new array with the data from the first array
         }
         data = temp; //makes data array the same as the temp array
      }
      if(!isCurrent()){ //if theres a special current element
      data[manyItems] = d; 
      currentIndex = manyItems; //current index is the index after the last item
      }
      else{
         for(int i = manyItems-1; i > currentIndex; i--){ 
            data[i+1] = data[i]; //move everything forward 1 to make space for the new value
         }
         data[currentIndex + 1] = d; //index after currentIndex = manyitems
         currentIndex = currentIndex+1;
      }
      manyItems++;
   }

   /**
    * Add a new element to this sequence, before the current element.
    * If the new element would take this sequence beyond its current capacity,
    * then the capacity is increased before adding the new element.
    * 
    * @param element
    *                the new element that is being added
    * @postcondition
    *                A new copy of the element has been added to this sequence. If
    *                there was
    *                a current element, then the new element is placed before the
    *                current
    *                element. If there was no current element, then the new element
    *                is placed
    *                at the start of the sequence. In all cases, the new element
    *                becomes the
    *                new current element of this sequence.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for increasing the
    *                             sequence's capacity.
    * @note
    *       An attempt to increase the capacity beyond
    *       Integer.MAX_VALUE will cause the sequence to fail with an
    *       arithmetic overflow.
    **/
   public void addBefore(double element) {
      if(manyItems == data.length){
         int newCapacity = data.length*2;  
         double[] temp = new double[newCapacity];
         for(int i = 0; i < data.length; i++){
            temp[i] = data[i];
         } //same as addBefore, duplicating the array and making it bigger
         data = temp;
      }
      if(!isCurrent()){
         for(int i = manyItems-1; i >= 0; i--){
            data[i+1] = data[i]; //make room for the new item
         }
      data[0] = element;
      currentIndex = 0;
      }
      else{
         for(int i = manyItems - 1; i >= currentIndex; i--){
            data[i+1] = data[i];
         }//if theres no current index then add the new item to the front
         data[currentIndex] = element;
      }
      manyItems++;
   }

   /**
    * Place the contents of another sequence at the end of this sequence.
    * 
    * @param addend
    *               a sequence whose contents will be placed at the end of this
    *               sequence
    * @precondition
    *               The parameter, addend, is not null.
    * @postcondition
    *                The elements from addend have been placed at the end of
    *                this sequence. The current element of this sequence remains
    *                where it
    *                was, and the addend is also unchanged.
    * @exception NullPointerException
    *                                 Indicates that addend is null.
    * @exception OutOfMemoryError
    *                                 Indicates insufficient memory to increase the
    *                                 size of this sequence.
    * @note
    *       An attempt to increase the capacity beyond
    *       Integer.MAX_VALUE will cause an arithmetic overflow
    *       that will cause the sequence to fail.
    **/
   public void addAll(DoubleArraySequence addend) {
      if(addend == null)
      throw new NullPointerException();// throw an exception if the capacity is beyond the max value
      data = catenation(this, addend).data; //run catenation
      manyItems = manyItems + addend.size(); //manyitems = the amount of items in the first array + the size of the second

   }

   /**
    * Move forward, so that the current element is now the next element in
    * this sequence.
    * 
    * @param - none
    * @precondition
    *               isCurrent() returns true.
    * @postcondition
    *                If the current element was already the end element of this
    *                sequence
    *                (with nothing after it), then there is no longer any current
    *                element.
    *                Otherwise, the new element is the element immediately after
    *                the
    *                original current element.
    * @exception IllegalStateException
    *                                  Indicates that there is no current element,
    *                                  so
    *                                  advance may not be called.
    **/
   public void advance() {
      if(!isCurrent()){
         throw new IllegalStateException();
      }
      if(currentIndex < manyItems){ //if the current index is less than the end of the array then advance current index by 1
         currentIndex++;
      }
   }

   /**
    * Generate a copy of this sequence.
    * 
    * @param - none
    * @return
    *         The return value is a copy of this sequence. Subsequent changes to
    *         the
    *         copy will not affect the original, nor vice versa.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for creating the
    *                             clone.
    **/

   /**
    * Create a new sequence that contains all the elements from one sequence
    * followed by another.
    * 
    * @param s1
    *           the first of two sequences
    * @param s2
    *           the second of two sequences
    * @precondition
    *               Neither s1 nor s2 is null.
    * @return
    *         a new sequence that has the elements of s1 followed by the
    *         elements of s2 (with no current element)
    * @exception NullPointerException
    *                                  Indicates that one of the arguments is null.
    * @exception OutOfMemoryError
    *                                  Indicates insufficient memory for the new
    *                                  sequence.
    * @note
    *       An attempt to create a sequence with a capacity beyond
    *       Integer.MAX_VALUE will cause an arithmetic overflow
    *       that will cause the sequence to fail.
    **/
   public static DoubleArraySequence catenation(DoubleArraySequence s1, DoubleArraySequence s2) {
      DoubleArraySequence temp;
      if (s1.size() + s2.size() > DEFAULT_CAPACITY) { 
         temp = new DoubleArraySequence(s1.size() + s2.size());
      } else {
         temp = new DoubleArraySequence();
      }
      for(int i = 0; i < s1.size(); i++){
         temp.data[i] = s1.data[i]; // add all the data from s1 to temp
      }
      for(int i = 0; i < s2.size(); i++){
         temp.data[i + s1.size()] = s2.data[i]; //add all the data from s2 to temp after s1
      }
      temp.manyItems = s1.size() + s2.size(); 
      temp.currentIndex = temp.manyItems;
      return temp; 
   }

   /**
    * Change the current capacity of this sequence.
    * 
    * @param minimumCapacity
    *                        the new capacity for this sequence
    * @postcondition
    *                This sequence's capacity has been changed to at least
    *                minimumCapacity.
    *                If the capacity was already at or greater than
    *                minimumCapacity,
    *                then the capacity is left unchanged.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for: new
    *                             int[minimumCapacity].
    **/
    public void ensureCapacity(int minimumCapacity) {
      if(data.length < minimumCapacity){  //if the length is less than minimum capacity
         double[] temp = new double[minimumCapacity]; //make a temp array thats the length of minimum capacity
         for(int i = 0; i < data.length; i++){
            temp[i] = data[i]; //now copy the data from data to temp
         }
         data = temp;//and then make data the same length as temp 
      }
   }


   /**
    * Accessor method to get the current capacity of this sequence.
    * The add method works efficiently (without needing
    * more memory) until this capacity is reached.
    * 
    * @param - none
    * @return
    *         the current capacity of this sequence
    **/
   public int getCapacity() {
      return data.length;
   } //simply returns the capacity of the array

   /**
    * Accessor method to get the current element of this sequence.
    * 
    * @param - none
    * @precondition
    *               isCurrent() returns true.
    * @return
    *         the current element of this sequence
    * @exception IllegalStateException
    *                                  Indicates that there is no current element,
    *                                  so
    *                                  getCurrent may not be called.
    **/
   public double getCurrent() {
      if(!isCurrent()) 
      throw new IllegalStateException(); //if you try to get current when there is no current throw an exception

      return data[currentIndex];
   }

   /**
    * Accessor method to determine whether this sequence has a specified
    * current element that can be retrieved with the getCurrent method.
    * 
    * @param - none
    * @return
    *         true (there is a current element) or false (there is no current
    *         element at the moment)
    **/
   public boolean isCurrent() { 
      return 0 <= currentIndex && currentIndex < manyItems;
   } //see if there is a current element

   /**
    * Remove the current element from this sequence.
    * 
    * @param - none
    * @precondition
    *               isCurrent() returns true.
    * @postcondition
    *                The current element has been removed from this sequence, and
    *                the
    *                following element (if there is one) is now the new current
    *                element.
    *                If there was no following element, then there is now no
    *                current
    *                element.
    * @exception IllegalStateException
    *                                  Indicates that there is no current element,
    *                                  so
    *                                  removeCurrent may not be called.
    **/
   public void removeCurrent() {
      if(!isCurrent())
      throw new IllegalStateException(); // if there isnt a current throw exception
      for(int i = currentIndex; i < manyItems -1 ; i++){
         data[i] = data[i+1]; //if there is a current move everything over then delete it
      }
      manyItems--; 
   }

   

   /**
    * Determine the number of elements in this sequence.
    * 
    * @param - none
    * @return
    *         the number of elements in this sequence
    **/
   public int size() { // Determine the number of elements in this sequence.
      return manyItems;
   }

   /**
    * Set the current element at the front of this sequence.
    * 
    * @param - none
    * @postcondition
    *                The front element of this sequence is now the current element
    *                (but
    *                if this sequence has no elements at all, then there is no
    *                current
    *                element).
    **/
   public void start() {
      currentIndex = 0;
   }

   /**
    * Reduce the current capacity of this sequence to its actual size (i.e., the
    * number of elements it contains).
    * 
    * @param - none
    * @postcondition
    *                This sequence's capacity has been changed to its current size.
    * @exception OutOfMemoryError
    *                             Indicates insufficient memory for altering the
    *                             capacity.
    **/
   public void trimToSize() {
      double[] temp = new double[manyItems];
      for(int i = 0; i < manyItems; i++){
         temp[i] = data[i];
      }
      data = temp;
   } //bring down the length of data to manyitems

   public int getCurrentIndex() {
      return currentIndex;
   }

   public void setCurrentIndex(int currentIndex) {
      this.currentIndex = currentIndex;
   } 
   // set the array to the current index

   public DoubleArraySequence(DoubleArraySequence src) { //creates a new doublearraysequence 
      data = new double[src.data.length]; 
      for(int i = 0; i < src.data.length; i++){
         data[i] = src.data[i]; //sets the old data into the new one
      }
      currentIndex = src.currentIndex;
      manyItems = src.manyItems;                                                         
   } 
}