#include <stdio.h>

int main(){
	int n, i, j;
	printf("Nhap so luong phan tu mang: ");
	scanf("%d", &n);
	int mang[n];
	for(i = 0; i < n; i++){
		printf("Nhap phan tu thu %d: ", i + 1);
		scanf("%d", &mang[i]);
	}
	
	for(i = 0; i < n - 1; i++){
		for(j = i; j < n; j++){
			if(mang[i] < mang[j]){
				// doi cho phan tu thu i va thu j
				int tmp;
				tmp = mang[i];
				mang[i] = mang[j];
				mang[j] = tmp;
			}
		}
	}
	printf("Mang sau khi sap xep la: ");
	for(i = 0; i < n; i++){
		printf("%d ", mang[i]);
	}
}
