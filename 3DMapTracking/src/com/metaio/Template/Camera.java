// Copyright 2007-2013 metaio GmbH. All rights reserved.
package com.metaio.Template;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.EVISUAL_SEARCH_STATE;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.IVisualSearchCallback;
import com.metaio.sdk.jni.ImageStruct;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.sdk.jni.VisualSearchResponseVector;
import com.metaio.tools.io.AssetsManager;

public class Camera extends ARViewActivity 
{

	private IGeometry mModel;
	private MetaioSDKCallbackHandler mSDKCallback;
	private VisualSearchCallbackHandler mVisualSearchCallback;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mSDKCallback = new MetaioSDKCallbackHandler();
		mVisualSearchCallback = new VisualSearchCallbackHandler();
		metaioSDK.registerVisualSearchCallback(mVisualSearchCallback);
	}

	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		mSDKCallback.delete();
		mSDKCallback = null;
		mVisualSearchCallback.delete();
		mVisualSearchCallback = null;
	}
	
	@Override
	protected int getGUILayout() 
	{
		// Attaching layout to the activity
		return R.layout.template; 
	}


	public void onButtonClick(View v)
	{
		finish();
	}
	
	
	@Override
	protected void loadContents() 
	{
		try
		{
			
			// Getting a file path for tracking configuration XML file
			String trackingConfigFile = AssetsManager.getAssetPath("scrivania.3dmap");
			
			// Assigning tracking configuration
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result); 
	        
		

			
			// Getting a file path for a 3D geometry
			String metaioManModel = AssetsManager.getAssetPath("vaso.obj");
		
			if (metaioManModel != null) 
			{
				// Loading 3D geometry
				mModel = metaioSDK.createGeometry(metaioManModel);
				if (mModel != null) 
				{
					// Set geometry properties
					mModel.setScale(new Vector3d(2.0f, 2.0f, 2.0f));
					
					
				}
				else
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "+metaioManModel);
			}
			
		}       
		catch (Exception e)
		{
			
		}
	}
	
  
	@Override
	protected void onGeometryTouched(IGeometry geometry) 
	{
		
	}


	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() 
	{
		return mSDKCallback;
	}
	
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback 
	{

		@Override
		public void onSDKReady() 
		{
			MetaioDebug.log("The SDK is ready");
		}
		
		@Override
		public void onAnimationEnd(IGeometry geometry, String animationName) 
		{
			MetaioDebug.log("animation ended" + animationName);
		}
		
		@Override
		public void onMovieEnd(IGeometry geometry, String name)
		{
			MetaioDebug.log("movie ended" + name);
		}
		
		@Override
		public void onNewCameraFrame(ImageStruct cameraFrame)
		{
			MetaioDebug.log("a new camera frame image is delivered" + cameraFrame.getTimestamp());
		}
		
		@Override 
		public void onCameraImageSaved(String filepath)
		{
			MetaioDebug.log("a new camera frame image is saved to" + filepath);
		}
		
		@Override
		public void onScreenshotImage(ImageStruct image)
		{
			MetaioDebug.log("screenshot image is received" + image.getTimestamp());
		}
		
		@Override
		public void onScreenshotSaved(String filepath)
		{
			MetaioDebug.log("screenshot image is saved to" + filepath);
		}
		
		@Override
		public void onTrackingEvent(TrackingValuesVector trackingValues)
		{
			MetaioDebug.log("The tracking time is:" + trackingValues.get(0).getTimeElapsed());
		}

		@Override
		public void onInstantTrackingEvent(boolean success, String file)
		{
			if (success)
			{
				MetaioDebug.log("Instant 3D tracking is successful");
			}
		}
	}
	
	final class VisualSearchCallbackHandler extends IVisualSearchCallback
	{

		@Override
		public void onVisualSearchResult(VisualSearchResponseVector response, int errorCode)
		{
			if (errorCode == 0)
			{
				MetaioDebug.log("Visual search is successful");
			}
		}

		@Override
		public void onVisualSearchStatusChanged(EVISUAL_SEARCH_STATE state) 
		{
			MetaioDebug.log("The current visual search state is: " + state);
		}


	}
	
}
