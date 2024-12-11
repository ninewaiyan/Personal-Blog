
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Blog.com</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    
    <style>


        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f6f9;
            color: #333;
            overflow-x: hidden; /* Prevent horizontal scroll */
        }


        
       
        /* Sidebar Styling */
        .sidebar {
            height: calc(100vh - 56px);
            background-color: #1f2a38;
            color: #ecf0f1;
            position: fixed;
            top:56px;
            left: 0;
            z-index: 1000;
            width: 250px;
            transition: transform 0.3s ease;
            transform: translateX(0);
        }
        .sidebar.collapsed {
            transform: translateX(-100%);
        }
        .sidebar a {
            color: #ecf0f1;
            text-decoration: none;
            padding: 15px 20px;
            display: block;
            transition: background-color 0.3s, padding-left 0.3s;
        }
        .sidebar a:hover {
            background-color: #34495e;
            padding-left: 30px;
        }

        /* Main Content Styling */
        .main-content {
            margin-left: 250px;
            transition: margin-left 0.3s ease;
            padding: 20px;
        }
        .main-content.collapsed {
            margin-left: 0;
        }

        /* Toggle Button Styling */
        .slider-btn {
            cursor: pointer;
            position: fixed;
            top: 50%;
            left: 250px;
            z-index: 1001;
            transform: translateY(-50%);
            background-color: #3498db;
            color: #fff;
            border: none;
           
            width: 20px;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 4px 8px rgba(0,0,0,0.3);
            transition: left 0.3s ease, background-color 0.3s ease;
        }
        .slider-btn.collapsed {
            left: 0;
        }
        .slider-btn:hover {
            background-color: #2980b9;
        }

        /* Responsive Adjustments */
        @media (max-width: px) {
            .sidebar {
                width: 200px;
                transform: translateX(-100%);
            }
            .sidebar.show {
                transform: translateX(0);
            }
            .main-content {
                margin-left: 0;
               
            }
            .slider-btn {
                top: 10%;
                left: 0;
                z-index: 1050; /* Ensure it's above other elements */
            }

    
        }

        body {
            background-color: #f8f9fa;
          }
          
          .post-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
          }
          
          .mediaDiv{
          
          justify-content: center;
          }
          
          .media{
          
           border-radius: 0 0 15px 15px;
           	margin-left :15%;
            width: 65%;
            height: 40%;
            max-height: 50%; /* Adjust height for images */
            object-fit: cover;
          }
          .carousel-inner img {
            max-height: 250px; /* Ensure images in carousel have proper height */
            object-fit: cover;
          }
          .post-title {
            font-weight: bold;
            color: #343a40;
          }
          .post-time {
            color: #7f8b95;
          }
          .post-content {
            color: #010101;
          }
          .post-card-footer {
            background-color: #fff;
            border-top: none;
            border-radius: 0 0 15px 15px;
          }
          .comment-input, .reply-input {
            border-radius: 30px;
            padding-left: 15px;
          }
          .comment-btn, .reply-btn {
            border-radius: 30px;
          }
          .dropdown-menu-end {
            right: 0;
            left: auto;
          }
          .dropdown-menu {
            border-radius: 10px;
            padding: 0;
          }
          .dropdown-item {
            padding: 10px 15px;
            border-radius: 10px;
          }
          .dropdown-item:hover {
            background-color: #f1f1f1;
          }
          .dropdown-toggle::after {
            display: none;
          }
          .post-actions {
            position: absolute;
            top: 15px;
            right: 15px;
          }
          .comment-section {
            max-height: 200px;
            overflow-y: auto;
            margin-top: 10px;
            display: none;
          }
          .comment, .reply {
            padding: 10px 15px;
            background-color: #f8f9fa;
            border-radius: 10px;
            margin-bottom: 10px;
          }
          .comment h6, .reply h6 {
            margin-bottom: 5px;
            font-weight: bold;
          }
          .comment p, .reply p {
            margin: 0;
          }
          .slider-btn, .reply-btn {
            cursor: pointer;
            color: #007bff;
            font-size: 14px;
          }
          .reply-section {
            margin-top: 10px;
            padding-left: 30px;
          }
          .reply-input-group {
            display: none;
          }
    </style>

