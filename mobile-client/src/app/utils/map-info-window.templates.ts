export const mapInfoWindowTemplates = `
<template key="registerTableTemplate">
  <GridLayout columns="*,*,*" rows="*,*,*,*,*" width="150" height="140">
    <Label text="{{userData.venueDetails.name}}" col="0" colSpan="3" height="20"></Label>
    <Label text="{{userData.venueDetails.location.city}}" row="1" colSpan="3" height="20"></Label>
    <Label text="{{userData.venueDetails.location.address}}" row="2" colSpan="3" height="20"></Label>
    <Button text="Zarezerwuj stolik" row="3" colSpan="3" rowSpan="2"
      style="background-color: #0099CC; margin: 5;" height="40">
    </Button>
  </GridLayout>
</template>
<template key="notLoggedTableTemplate">
  <GridLayout columns="*,*,*" rows="*,*,*,*,*" width="150" height="140">
    <Label text="{{userData.venueDetails.name}}" col="0" colSpan="3" height="20"></Label>
    <Label text="{{userData.venueDetails.location.city}}" row="1" colSpan="3" height="20"></Label>
    <Label text="{{userData.venueDetails.location.address}}" row="2" colSpan="3" height="20"></Label>
    <Label text="Zaloguj się, aby zarezerwować" row="3" colSpan="3" rowSpan="2"
      style="color: #d80a0a; margin: 5;" height="40" textWrap="true">
    </Label>
  </GridLayout>
</template>
<template key="venueTemplate">
  <GridLayout columns="*,*,*" rows="*,*,*" width="150" height="70">
    <Label text="{{userData.venueDetails.name}}" col="0" colSpan="3" height="20"></Label>
    <Label text="{{userData.venueDetails.location.city}}" row="1" colSpan="3" height="20"></Label>
    <Label text="{{userData.venueDetails.location.address}}" row="2" colSpan="3" height="20"></Label>
  </GridLayout>
</template>`;
