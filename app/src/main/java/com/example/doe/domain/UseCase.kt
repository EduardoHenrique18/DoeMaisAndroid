package com.example.doe.domain

interface UseCase<Input, Output> {
    fun run(input: Input, callback: (Output) -> Unit)
}