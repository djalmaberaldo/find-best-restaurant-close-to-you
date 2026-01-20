#!/bin/bash

BASE_URL="http://localhost:8080/api/restaurants"

echo "== Restaurant Matcher API Tests =="

echo ""
echo "1) All restaurants no filters"
curl -s "$BASE_URL" | jq .

echo ""
echo "2) Filter by cuisine=Italian"
curl -s "$BASE_URL?cuisineName=Italian" | jq .

echo ""
echo "2) Filter by cuisine=Italian"
curl -s "$BASE_URL?cuisineName=Italian" | jq .

echo ""
echo "3) Filter by distance=5"
curl -s "$BASE_URL?distance=5" | jq .

echo ""
echo "4) Filter by price=30"
curl -s "$BASE_URL?price=30" | jq .

echo ""
echo "5) Filter by rating=4"
curl -s "$BASE_URL?customerRating=4" | jq .

echo ""
echo "6) Combined filters"
curl -s "$BASE_URL?cuisineName=Italian&distance=5&price=40&customerRating=3" | jq .

echo ""
echo "7) Filter by name contains 'pizza'"
curl -s "$BASE_URL?restaurantName=pizza" | jq .

echo ""
echo "8) Filter by cuisine=Chinese"
curl -s "$BASE_URL?cuisineName=Chinese" | jq .

echo ""
echo "== Done =="
