package com.seekret.html.request.impl;

public class PrepareNothingHandler extends AbstractHtmlRequestHandler {

	@Override
	protected boolean isLoginRequired() {
		return false;
	}
}
