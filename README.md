# Musizbrainz mashup

An api for gathering artist data from wikidata, wikipedia and musicbrainz alongside album covers from the cover art archive.
This is an api written in Java Spring Boot initialized with the Spring boot initializer. The api uses restemplate to handle the extern api requests.

## List of used Apis:

### Musizbrainz:
http://musicbrainz.org/doc/Development/XML_Web_Service/Version_2
* URL: http://musicbrainz.org/ws/2
* Example:
http://musicbrainz.org/ws/2/artist/5b11f4ce-a62d-471e-81fc-a69a8278c7da?&fmt=json&inc=url-rels+release-groups

### Wikidata
https://www.wikidata.org/w/api.php

* URL till API: https://www.wikidata.org/w/api.php
* Exempelfråga:
https://www.wikidata.org/w/api.php?action=wbgetentities&ids=Q11649&format=json&props=sitelinks

### Wikipedia
https://www.mediawiki.org/wiki/API:Main_page
* URL till API: https://en.wikipedia.org/w/api.php
* Exempelfråga:
https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=Nirvana_(band)

### Coverart Archive
https://wiki.musicbrainz.org/Cover_Art_Archive/API
* URL till API: http://coverartarchive.org/
* Exempelfråga:
http://coverartarchive.org/release-group/1b022e01-4da6-387b-8658-8678046e4cef