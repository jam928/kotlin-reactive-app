package com.kotlinreactiveapp

import com.kotlinreactiveapp.model.Post
import com.kotlinreactiveapp.repository.PostMongoReactiveRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PostsDataLoader(val postMongoReactiveRepository: PostMongoReactiveRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val count = postMongoReactiveRepository.count().block()
        print(count)
        if(count != 0L) return

        val randomTitles = arrayOf("This is a title", "random title", "Yes this is a title", "Not a long title")
        val randomBody = arrayOf("This is a body", "Random body", "This is the only body", "Not a long body to read")
        val randomAuthors = arrayOf("Joe Smith", "John Doe", "Billy Bob", "Jane Doe")

        val posts = arrayListOf<Post>()
        for(i in 0..2000) {
            val randomIndex = (0..3).random()
            val id = UUID.randomUUID().toString()
            val post = Post(id, randomTitles[randomIndex], randomBody[randomIndex], randomAuthors[randomIndex])
            posts.add(post)
        }

        postMongoReactiveRepository.saveAll(posts).subscribe({post -> println("Post Saved: ${post}")})
        println("Repository now contains: ${postMongoReactiveRepository.count().block()}")
    }
}