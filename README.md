| **Method** | **Endpoint**                                       | **Purpose**                                   |
|------------|----------------------------------------------------|-----------------------------------------------|
| **POST**   | `/blogs`                                           | Create a new blog                             |
| **GET**    | `/blogs`                                           | Get all blogs                                 |
| **GET**    | `/blogs/{id}`                                      | Get a specific blog                           |
| **PUT**    | `/blogs/{id}`                                      | Update a blog                                 |
| **DELETE** | `/blogs/{id}`                                      | Delete a blog                                 |
| **GET**    | `/blogs/recent`                                    | Get the 5 most recent blogs                   |
| **GET**    | `/blogs/daterange?start=YYYY-MM-DD&end=YYYY-MM-DD` | Get blogs within a date range                 |
| **GET**    | `/categories`                                      | Get all available categories                  |
| **GET**    | `/blogs/category/{category-name}`                  | Get blogs by category                         |
| **POST**   | `/blogs/{id}/comments`                             | Add a comment to a blog                       |
| **GET**    | `/blogs/{id}/comments`                             | Get all comments for a blog                   |
| **GET**    | `/blogs/most-commented`                            | Get blogs with the highest number of comments |
| **GET**    | `/blogs/author/{authorName}`                       | Get blogs by a specific author                |
| **PUT**    | `/blogs/author/{authorName}/update`                | Update author information                     |
| **DELETE** | `/blogs/author/{authorName}/delete`                | Delete all blogs by a specific author         |
