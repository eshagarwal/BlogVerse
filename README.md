| **Method** | **Endpoint**                                       | **Purpose**                                     |
|------------|----------------------------------------------------|-------------------------------------------------|
| **POST**   | `/blogs`                                           | Create a new blog                               |
| **GET**    | `/blogs`                                           | Retrieve all blogs                              |
| **GET**    | `/blogs/{id}`                                      | Retrieve a specific blog                        |
| **PUT**    | `/blogs/{id}`                                      | Update a blog                                   |
| **DELETE** | `/blogs/{id}`                                      | Delete a blog                                   |
| **GET**    | `/blogs/recent`                                    | Fetch the 5 most recent blogs                   |
| **GET**    | `/blogs/daterange?start=YYYY-MM-DD&end=YYYY-MM-DD` | Fetch blogs within a date range                 |
| **GET**    | `/categories`                                      | Fetch all available categories                  |
| **GET**    | `/blogs/category/{category-name}`                  | Fetch blogs by category                         |
| **GET**    | `/blogs/most-commented`                            | Fetch blogs with the highest number of comments |
| **POST**   | `/blogs/{id}/comments`                             | Add a comment to a blog                         |
| **GET**    | `/blogs/{id}/comments`                             | Retrieve all comments for a blog                |
| **POST**   | `/blogs/{id}/comments/{commentId}/report`          | Report an inappropriate comment                 |
| **POST**   | `/blogs/{id}/save`                                 | Save/bookmark a blog                            |
