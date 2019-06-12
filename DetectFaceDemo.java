package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.sun.deploy.uitoolkit.impl.fx.Utils;

import sun.rmi.runtime.Log;

public class DetectFaceDemo {
	  public void run() {
		    System.out.println("\nRunning DetectFaceDemo");

		    // Create a face detector from the cascade file in the resources
		    // directory.
		    CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
		    Mat image = Imgcodecs.imread("test3.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

		    // Detect faces in the image.
		    // MatOfRect is a special container class for Rect.
		    MatOfRect faceDetections = new MatOfRect();
		    faceDetector.detectMultiScale(image, faceDetections);

		    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

		    // Draw a bounding box around each face.
	
		    for (Rect rect : faceDetections.toArray()) {
		        Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 255, 10));
		   }
		    
		  Imgproc.GaussianBlur(image, image, new Size(11,11),0);
		   Imgproc.Canny(image, image, 2, 6, 3, true); 
		  
		   List<MatOfPoint> contours = new ArrayList<>();
		   Scalar white = new Scalar(255,255,100);
		   
		   Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		   Imgproc.drawContours(image, contours, -1, white);
		   
		  /* for (MatOfPoint contour: contours) 
			   Imgproc.fillPoly(image, Arrays.asList(contour), white); 
		   
		   Imgproc.HoughLinesP(image, image, 5, Math.PI/180, 70, 30, 10);*/


		  // List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();    
	      // Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		   /*int max = 0;
		   int m = 0;
		   for (int i = 0; i < contours.size(); i++)
		   {
			   if (Imgproc.contourArea(contours.get(i)) > max) {
				   max = (int) Imgproc.contourArea(contours.get(i));
				   m =i;
			   }
		   }*/
	       /* for(int i=0; i< contours.size();i++){
	            if (Imgproc.contourArea(contours.get(i)) > 70 ){
	                Rect rect = Imgproc.boundingRect(contours.get(i));
	                if (rect.height > 50){
	                    Imgproc.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
	                    Mat ROI = image.submat(rect.y, rect.y + rect.height, rect.x, rect.x + rect.width);

	                    Imgcodecs.imwrite("test3.png",ROI);

	                }
	            }
	        }*/
		   
		  /* Rect rect = Imgproc.boundingRect(contours.get(m));
           if (rect.height > 50){
               Imgproc.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
               Mat ROI = image.submat(rect.y, rect.y + rect.height, rect.x, rect.x + rect.width);

               Imgcodecs.imwrite("test3.png",ROI);
           }
		   */
		   String filename = "faceDetection.png";
		    System.out.println(String.format("Writing %s", filename));
		    Imgcodecs.imwrite(filename, image);
		  }
	  }


