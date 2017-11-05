import pyautogui as py
import cv2

distance = 100
py.alert('This displays some text with an OK button.')
py.moveTo(324,132,0.5,py.easeInElastic)
#py.dragRel(distance, 0, duration=0.5)
#py.click(x=394, y=308, clicks=2, interval=0, button='left')
while distance > 0:
    py.dragRel(distance, 0, duration=0.5, button='left')   # move right
    distance -= 5
    py.dragRel(0, distance, duration=0.5, button='left')   # move down
    py.dragRel(-distance, 0, duration=0.5, button='left')  # move left
    distance -= 5
    py.dragRel(0, -distance, duration=0.5, button='left')  # move up
