package com.example.healthcare.data.common.utils

import android.util.Log
import com.example.healthcare.common.NoNetworkException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class ResultMaker<T : Any,U : Any> {
    fun getResult(response: Response<U>?) : Flow<BaseResult<T, WrappedResponse<U>>> {
        if (response == null){
            throw NoNetworkException()
        }
        return flow {
            if (response.isSuccessful){
                val body = response.body()
                Log.i("response_tag",response.body().toString())
                val model = body?.let {
                    getModel(it)
                }
                if(model == null) {
                    Log.e("model","model is null")
                }else {

                    emit(BaseResult.Success(model))
                }
            } else {
                val err = getWrappedResponse(response)
                emit(BaseResult.Error(err))
            }
        }
    }

    fun getListResult(response: Response<List<U>>?): Flow<BaseResult<List<T>, WrappedListResponse<U>>>{
        if (response == null){
            throw NoNetworkException()
        }
        return flow {
            if (response.isSuccessful){
                val body = response.body()
                val modelList = mutableListOf<T>()
                body?.forEach{
                    modelList.add(getModel(it))
                }
                Log.i("flow_tag","successful ${response.body()}")
                emit(BaseResult.Success(modelList))
            } else {
                val err = getWrappedListResponse(response)
                emit(BaseResult.Error(err))
                Log.i("flow_tag","not successful")

            }
        }
    }

    protected abstract fun getModel(res: U) : T

    private fun getWrappedResponse(response: Response<U>): WrappedResponse<U> {
        Log.e("response",response.code().toString()+response.body())
        val err = WrappedResponse(
            response.code(),
            response.message(),
            response.isSuccessful,
            null,
            response.body()
        )
        return err
    }

    private fun getWrappedListResponse(response: Response<List<U>>): WrappedListResponse<U> {
        val type = object : TypeToken<WrappedListResponse<U>>(){}.type
        val err = Gson().fromJson<WrappedListResponse<U>>(response.errorBody()!!.charStream(), type)!!
        err.code = response.code()
        return err
    }
}