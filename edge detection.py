import cv2
import numpy as np

# Load our input image
image = cv2.imread('input.jpg',0)
height,width =image.shape;

#sobel_x=cv2.Sobel(image, cv2.CV_64F,0,1, ksize=5);
#sobel_y=cv2.Sobel(image, cv2.CV_64F,1,0, ksize=5);

#cv2.imshow("Sobel X", sobel_x);
#cv2.waitKey()

#cv2.imshow("Sobel Y", sobel_y);
#cv2.waitKey()

#sober_or=cv2.bitwise_or(sobel_x, sobel_x);
#cv2.imshow('or', sober_or)

laplacian=cv2.Laplacian(image, cv2.CV_64F);
#cv2.imshow('or', laplacian)


canny=cv2.Canny(image,0, 50);
cv2.imshow('canny', canny)

#cv2.imshow('image', image)




cv2.waitKey()
cv2.destroyAllWindows()

 
