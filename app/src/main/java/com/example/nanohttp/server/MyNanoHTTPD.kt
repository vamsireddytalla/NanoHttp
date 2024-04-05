package com.example.nanohttp.server

import fi.iki.elonen.NanoHTTPD

class MyNanoHTTPD : NanoHTTPD(8181)
{
    override fun serve(session: IHTTPSession): Response
    {
        val msg = "Hello, Android Server!"
        return newFixedLengthResponse(msg)
    }

}