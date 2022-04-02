import React, {useCallback, useState} from 'react'
import {GoogleMap, useJsApiLoader} from '@react-google-maps/api';
import {getResolution} from "../functions/getResolution";

const containerStyle = getResolution()

const center = {
  lat: 52.13,
  lng: 21.0
};

const Map = () => {
  const {isLoaded} = useJsApiLoader({
    id: 'google-map-script',
    googleMapsApiKey: 'AIzaSyCC7l2an9afwuF1LcCCmhGS0g72zLX0vJ4'
  })

  const [map, setMap] = useState(null)

  const onLoad = useCallback(function callback(map) {
    const bounds = new window.google.maps.LatLngBounds();
    map.fitBounds(bounds);
    setMap(map)
  }, [])

  const onUnmount = useCallback(function callback(map) {
    setMap(null)
  }, [])

  return isLoaded ? (
    <GoogleMap
      mapContainerStyle={containerStyle}
      center={center}
      zoom={10}
      onLoad={onLoad}
      onUnmount={onUnmount}
    >
      { /* Child components, such as markers, info windows, etc. */}
      <></>
    </GoogleMap>
  ) : <></>
}

export default Map