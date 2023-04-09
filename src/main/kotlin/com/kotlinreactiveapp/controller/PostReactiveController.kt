package com.kotlinreactiveapp.controller

import com.kotlinreactiveapp.model.Post
import com.kotlinreactiveapp.repository.PostMongoReactiveRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class PostReactiveController(val postMongoReactiveRepository: PostMongoReactiveRepository) {
    val DELAY_PER_ITEM_MS = 100

    @GetMapping("/posts-reactive")
    fun getPostsFlux(): Flux<Post> {
        return postMongoReactiveRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS.toLong()))
    }

    @GetMapping("/posts-reactive-paged")
    fun getPostsFluxPaged(@RequestParam page: Int,
                          @RequestParam size: Int): Flux<Post> {
        return postMongoReactiveRepository
            .findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size))
            .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS.toLong()))
    }

}