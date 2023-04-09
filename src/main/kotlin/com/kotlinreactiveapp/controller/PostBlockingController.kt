package com.kotlinreactiveapp.controller

import com.kotlinreactiveapp.model.Post
import com.kotlinreactiveapp.repository.PostMongoBlockingRepository
import com.kotlinreactiveapp.repository.PostMongoReactiveRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class PostBlockingController(val postMongoBlockingRepository: PostMongoBlockingRepository) {
    val DELAY_PER_ITEM_MS = 100

    @GetMapping("/posts-blocking")
    fun getPostsBlocking(): Iterable<Post> {
        Thread.sleep(DELAY_PER_ITEM_MS * postMongoBlockingRepository.count())
        return postMongoBlockingRepository.findAll()
    }

    @GetMapping("/posts-blocking-paged")
    fun getPostsBlocking(@RequestParam page: Int,
                          @RequestParam size: Int): Iterable<Post> {
        Thread.sleep(DELAY_PER_ITEM_MS * postMongoBlockingRepository.count())
        return postMongoBlockingRepository
            .findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size))
    }

}