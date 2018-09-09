
import imutils
import numpy as np
import argparse
import cv2

cap=cv2.VideoCapture(0)


while True:
    ret, frame=cap.read()
    
    orig =frame.copy()
    ratio = frame.shape[0] / 300.0
    frame = imutils.resize(frame, height = 300)
    cv2.imshow("frame", frame)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    gray = cv2.bilateralFilter(gray, 11, 17, 17)
    cv2.imshow('filtered', gray);
    edged = cv2.Canny(gray, 30, 200)
    edge_copy=edged.copy()
    cv2.imshow('edged', edged)
    
    image, cnts, hierarchy = cv2.findContours(edge_copy , cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    cnts = sorted(cnts, key = cv2.contourArea, reverse = True)[:10]
    screenCnt = None
    for c in cnts:
        peri = cv2.arcLength(c ,True)
        approx = cv2.approxPolyDP(c, 0.02 * peri, True)
        if len(approx) == 4:
            screenCnt = approx
            break

    print (screenCnt)

    cv2.drawContours(orig, cnts, -1, (0, 255, 0), 10)
    cv2.imshow("Game Boy Screen", orig)
    
    k=cv2.waitKey(1);
    if k == 27:
            break
    


cap.release();
cv2.destroyAllWindows();
