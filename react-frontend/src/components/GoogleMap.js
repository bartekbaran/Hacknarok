import React from 'react'
import {GoogleMap, LoadScript, useJsApiLoader} from '@react-google-maps/api';
import {getResolution} from "../functions/getResolution";

const containerStyle = getResolution()

const center = {
  lat: 50.05,
  lng: 19.93
};

const Map = () => {
  return (
    <LoadScript
      googleMapsApiKey="AIzaSyCC7l2an9afwuF1LcCCmhGS0g72zLX0vJ4"
    >
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={center}
        zoom={12}
      >
        { /* Child components, such as markers, info windows, etc. */}
        <></>
      </GoogleMap>
    </LoadScript>
  )
}

export default Map