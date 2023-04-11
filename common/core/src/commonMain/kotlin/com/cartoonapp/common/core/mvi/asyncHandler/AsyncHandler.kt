package com.cartoonapp.common.core.mvi.asyncHandler

interface AsyncHandler<INPUT_ACTION, OUTPUT_ACTION> {
    fun asyncActionStreamListener(asyncActionStream: INPUT_ACTION)
    fun observeActionStream(): OUTPUT_ACTION
}