package com.kotlinreactiveapp.repository

import com.kotlinreactiveapp.model.Post
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface PostMongoReactiveRepository : ReactiveSortingRepository<Post, String> {
    fun findAllByIdNotNullOrderByIdAsc(page: Pageable) : Flux<Post>
}