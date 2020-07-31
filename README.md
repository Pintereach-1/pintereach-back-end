Data Schema:

Base url:
https://karminer60-pintereach.herokuapp.com/

User data shape

    userid:  // generated by database, long
    username:// String      
    password:// String

Category Data Shape
    categoryid: // generated by database, long
   categoryName: //String

Article Data Shape
    articleid: // generated by database, long
    url: // String
    title: //String
    description: // String

Here are all the user endpoints:
| POST   | /createnewuser   |
| POST   |  /login      |
| GET   |  /logout      | revokes authorization token
| GET    | /users/users          | Get all users
| GET    | /users/user/{userid}      | Get user by user id
| GET    | /users/user/name/{username} | Get user by username
| GET    | /users/user/name/like/{username} | Get user using part of username
| POST   |/users/user | Post a new user
| PUT    | /users/user/{userid}      | Edit a user using user id
| DELETE | /users/user/{userid}      | Delete a user using user id

Here are all the article endpoints:
*All requests restricted to data belonging to logged in user
| GET    | /articles/articles          | Get all articles
| GET    | /articles/article/{articleid}      | Get an article by id
| POST   | /articles/category/{categoryid} | Post an article into a category
| PUT    | /articles/article/{articleid}      | Edit an article using  article id
| DELETE | /articles/article/{articleid}      | Delete an article using article id

Here are all the category endpoints:
*All requests restricted to data belonging to logged in user
| GET    | /categories/categories          | Get all categories belonging to logged in user
| GET    | /categories/category/{categoryid}      | Get one category using category id
| POST   | /categories/category | Post a category
| PUT    | /categories/category/{categoryid}      | Edit a category
| DELETE | /categories/category/{categoryid}      | Delete a category
