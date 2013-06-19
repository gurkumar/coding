Creating a text analyzer that provides information about its word content. It should support the ability to create a report
that shows a count of how many times each word appears.  The report should be sorted with primary sort of word length and 
secondary ASCII sort.

The approach taken towards solving this solution is without using the Collections framework for sorting purpose.
Map data structure is extensively used to store work count with each key entry being a word and value being the word count. 
This elimiates the duplicates as if the word key is already present in the map, the count is simply incremneted by one.

For sorting purpose, TreeMap data structure is used to preserve the sort order by length. All words with same length are
stored as a list of Strings with key into the Map being the word length. To addresses the ASCII secondarily sort, words with
same length are sorted lexicographicaly to acheive the ASCII order.

Another alternate approach that I was getting inclined towards was Tries where each node is a Character in a word and
words with common Characters have a common ancestors. Sorting was an expensive procedure using Tries as extensive Tree
Traversal was required. BurstSort algorithm, is recommended as a good optimization for String sorting which uses Tries to
store the words.
