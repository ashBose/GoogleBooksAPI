# GoogleBooksAPI

Objective:
The objective of this assignment is to build and test a command line application (app) with the data that returned from the Google books API. We will be using a simple unauthenticated Google books API to get a list of books and build a library with these books. Please adhere to good coding practices and code quality.

API:
Please copy and paste this link https://www.googleapis.com/books/v1/volumes?q=python on any web browser and see what it returns.

This API can also take additional parameter such as https://www.googleapis.com/books/v1/volumes?q=python&maxResults=40

More information about the API can be found here: https://developers.google.com/books/docs/v1/reference/volumes/list


Tests Requirements:
```
    Include automated tests for your project.
 ```

Functional Requirements:
```
    The app should prompt the user for a search string. (you may use popular search strings like 'dogs','cats','New York', ‘Java’... etc.)
    The app should allow a user to add specific books from the search results to a virtual bookshelf
    The app should also be able to persist bookshelf to disk
    The app should be able to load virtual bookshelf from step #3
    The user should be able to display the books in the virtual bookshelf sorted by price, avg rating, rating count, published date, or page count
```
