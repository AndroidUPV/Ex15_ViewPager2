# Ex15_ViewPager2
Lecture 02 - Development of Graphical User Interfaces (GUI)

A NavigationBar provides access to a ViewPager2 with two different adapters that enable the navigation through a number of fragments:
- The first adapter can only be used with a fixed number of fragments
- The second adapter can dynamically add/remove fragments. It must override the getItemId() and containsItem() methods.
- Swiping provides sequential access to fragments and random access is provided by a TabLayout. 
