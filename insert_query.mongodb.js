// MongoDB Playground
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.

// The current database to use.
use("blogverse");

// create collection
db.createCollection("blogs");

// insert blog data
db.blogs.insertMany([
    {
        title: "The Rise of AI in Everyday Life",
        content: "AI is transforming our daily experiences...",
        author: "John Doe",
        category: "Technology",
        createdAt: new Date("2025-03-01T10:00:00"),
        comments: []
    },
    {
        title: "Healthy Eating Habits",
        content: "Eating healthy is more than a diet...",
        author: "Jane Smith",
        category: "Health",
        createdAt: new Date("2025-03-02T12:30:00"),
        comments: []
    },
    {
        title: "Exploring the Beauty of Paris",
        content: "Paris, the city of love, offers...",
        author: "Alice Johnson",
        category: "Travel",
        createdAt: new Date("2025-03-03T14:15:00"),
        comments: []
    },
    {
        title: "Investing 101: A Beginner’s Guide",
        content: "Investing is essential for financial growth...",
        author: "Robert Brown",
        category: "Finance",
        createdAt: new Date("2025-03-04T09:45:00"),
        comments: []
    },
    {
        title: "Understanding Mental Health",
        content: "Mental health is just as important as physical health...",
        author: "Emily White",
        category: "Health",
        createdAt: new Date("2025-03-05T08:20:00"),
        comments: []
    },
    {
        title: "The Evolution of Web Development",
        content: "Web development has come a long way...",
        author: "Michael Lee",
        category: "Technology",
        createdAt: new Date("2025-03-06T16:40:00"),
        comments: []
    },
    {
        title: "Solo Traveling: Pros and Cons",
        content: "Traveling alone can be an enriching experience...",
        author: "Olivia Martinez",
        category: "Travel",
        createdAt: new Date("2025-03-07T11:10:00"),
        comments: []
    },
    {
        title: "Budgeting for Young Adults",
        content: "Managing finances in your 20s is crucial...",
        author: "Daniel Wilson",
        category: "Finance",
        createdAt: new Date("2025-03-08T15:05:00"),
        comments: []
    },
    {
        title: "How to Stay Fit Without a Gym",
        content: "Fitness is possible without expensive gym memberships...",
        author: "Sophia Anderson",
        category: "Health",
        createdAt: new Date("2025-03-09T13:55:00"),
        comments: []
    },
    {
        title: "Cloud Computing Explained",
        content: "Cloud computing is shaping the future of IT...",
        author: "David Thomas",
        category: "Technology",
        createdAt: new Date("2025-03-10T18:30:00"),
        comments: []
    },
    {
        title: "A Guide to Remote Work Success",
        content: "Remote work is here to stay, and here’s how to thrive...",
        author: "Jessica Clark",
        category: "Business",
        createdAt: new Date("2025-03-11T09:25:00"),
        comments: []
    },
    {
        title: "Best Travel Destinations in 2025",
        content: "Looking for new places to explore this year?",
        author: "William Rodriguez",
        category: "Travel",
        createdAt: new Date("2025-03-12T10:50:00"),
        comments: []
    },
    {
        title: "The Future of Cryptocurrencies",
        content: "Is cryptocurrency the future of money?",
        author: "Christopher Walker",
        category: "Finance",
        createdAt: new Date("2025-03-13T11:35:00"),
        comments: []
    },
    {
        title: "Mindfulness Meditation Benefits",
        content: "Practicing mindfulness can help reduce stress...",
        author: "Natalie Scott",
        category: "Health",
        createdAt: new Date("2025-03-14T17:00:00"),
        comments: []
    },
    {
        title: "Cybersecurity in the Modern World",
        content: "Cyber threats are increasing, and here’s how to stay safe...",
        author: "Benjamin Harris",
        category: "Technology",
        createdAt: new Date("2025-03-15T07:50:00"),
        comments: []
    },
    {
        title: "Freelancing vs. Full-Time Jobs",
        content: "Which career path is right for you?",
        author: "Hannah Turner",
        category: "Business",
        createdAt: new Date("2025-03-16T14:20:00"),
        comments: []
    },
    {
        title: "Best Hiking Trails in Europe",
        content: "Europe is home to some of the best hiking trails...",
        author: "Ryan Cooper",
        category: "Travel",
        createdAt: new Date("2025-03-17T12:10:00"),
        comments: []
    },
    {
        title: "The Psychology of Money",
        content: "Understanding how we think about money...",
        author: "Lauren Phillips",
        category: "Finance",
        createdAt: new Date("2025-03-18T09:30:00"),
        comments: []
    },
    {
        title: "Tech Trends to Watch in 2025",
        content: "From AI to quantum computing, here’s what’s next...",
        author: "Ethan King",
        category: "Technology",
        createdAt: new Date("2025-03-19T16:15:00"),
        comments: []
    },
    {
        title: "Work-Life Balance Strategies",
        content: "How to maintain balance in a busy world...",
        author: "Sarah Mitchell",
        category: "Business",
        createdAt: new Date("2025-03-20T08:55:00"),
        comments: []
    }
]);
