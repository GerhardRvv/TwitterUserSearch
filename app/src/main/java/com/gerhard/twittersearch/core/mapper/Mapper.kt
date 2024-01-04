package com.gerhard.twittersearch.core.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}