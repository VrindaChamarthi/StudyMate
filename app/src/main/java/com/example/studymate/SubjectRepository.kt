package com.example.studymate

class subjectRepository {
    suspend fun getSubjects(): List<Subjects> { // fun takes time to give response, it might not be immediate
        return listOf(
            Subjects("DAA","https://miro.medium.com/v2/resize:fit:1140/1*DktBDVw2QDoa5L7pBVdT7A.jpeg"),
            Subjects("CD", "https://s3.ap-south-1.amazonaws.com/www.virtulearn.in/CD.jpg"),
            Subjects("IOT",  "https://www.freecodecamp.org/news/content/images/2019/08/internet_of_things_iot.jpg"),
            Subjects("ML", "https://png.pngtree.com/background/20240413/original/pngtree-abstract-3d-rendering-of-blue-matrix-coding-a-digital-background-for-picture-image_8468945.jpg"),
            Subjects("SL", "https://c8.alamy.com/comp/2C46189/internet-code-programming-and-scripting-concept-2C46189.jpg")
        )
    }
}
