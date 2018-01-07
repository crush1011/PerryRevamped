package AutonomousTrajectories;

import java.util.ArrayList;

public class momentsClass{
	public static double[][] objects = new double[][]{
		{0.02 , 4.149478489656277 , 3.850521510343722 , 0.004177567761581953},
		{0.04 , 8.299731946934234 , 7.7002680530657655 , 0.018838094973433434},
		{0.06 , 12.452364982020104 , 11.547635017979896 , 0.04411194101341753},
		{0.08 , 16.608807408470504 , 15.391192591529492 , 0.0802233898980525},
		{0.1 , 20.77032286370512 , 19.229677136294868 , 0.1253302152522051},
		{0.12 , 24.938311477860193 , 23.06168852213988 , 0.1819439979578715},
		{0.14 , 29.11422321544341 , 26.885776784556565 , 0.24832869783747658},
		{0.16 , 33.299977972732016 , 30.70002202726798 , 0.3295018578140105},
		{0.18 , 37.49733420311481 , 34.502665796885445 , 0.421644396675916},
		{0.2 , 41.707639314650166 , 38.29236068534973 , 0.5277821807741796},
		{0.22 , 45.9332828303315 , 42.06671716966852 , 0.6488231536019242},
		{0.24 , 50.176397797050356 , 45.82360220294983 , 0.7857958453515533},
		{0.26 , 54.438573203199205 , 49.56142679680098 , 0.9372487091549838},
		{0.28 , 58.722894940867995 , 53.277105059131635 , 1.1095997029821614},
		{0.3 , 63.032957220396916 , 56.967042779603354 , 1.3017590396893572},
		{0.32 , 67.37106686399267 , 60.628933136007426 , 1.5153194208523153},
		{0.34 , 71.7416075031527 , 64.25839249684694 , 1.755060089056499},
		{0.36 , 76.14743236263914 , 67.85256763736152 , 2.0170148698039356},
		{0.38 , 80.5927186796226 , 71.40728132037776 , 2.3095118063383286},
		{0.4 , 85.08483245226822 , 74.91516754773168 , 2.635361413654155},
		{0.42 , 89.62827188387318 , 78.37172811612426 , 2.994090650668698},
		{0.44 , 94.22943145967469 , 81.77056854032513 , 3.392417322005025},
		{0.46 , 98.89731781912397 , 85.10268218087441 , 3.834158155973442},
		{0.48 , 103.64066674979725 , 88.35933325020474 , 4.323556205461492},
		{0.5 , 108.47160626625502 , 91.52839373374479 , 4.869746352584527},
		{0.52 , 113.40217313857082 , 94.59782686143315 , 5.474064203571788},
		{0.54 , 118.44366899101259 , 97.55633100898925 , 6.142415535083537},
		{0.56 , 123.61694256431223 , 100.38305743568885 , 6.891872502913048},
		{0.58 , 128.94413499852388 , 103.05586500147234 , 7.726152890758667},
		{0.6 , 134.44466972118423 , 105.55533027881812 , 8.654661177473683},
		{0.62 , 140.14748916579072 , 107.85251083421036 , 9.694284204521868},
		{0.64 , 146.08644763873562 , 109.91355236126401 , 10.85812230716791},
		{0.66 , 150.84553181967644 , 110.639861132548 , 12.160881601969416},
		{0.68 , 150.91859839902278 , 107.59185138074601 , 13.587711101748006},
		{0.7000000000000001 , 150.97879948876064 , 104.34047313236708 , 15.124795540217294},
		{0.72 , 151.03772859419604 , 100.92809037315557 , 16.778766508459302},
		{0.74 , 151.0874346378206 , 97.37558162934198 , 18.546594202943062},
		{0.76 , 151.13642098878825 , 93.71374799694024 , 20.442798221654478},
		{0.78 , 151.1699655618115 , 89.96742124648229 , 22.462118072055627},
		{0.8 , 151.19020313890232 , 86.1915534148423 , 24.60725592293773},
		{0.8200000000000001 , 151.1934109191673 , 82.43705163715994 , 26.879602213192157},
		{0.84 , 151.1696567997772 , 78.76966796962176 , 29.266063479510912},
		{0.86 , 151.12810546184113 , 75.26213732325647 , 31.775894306938785},
		{0.88 , 151.0485804183004 , 71.98442082960631 , 34.37641488582673},
		{0.9 , 150.9482067564242 , 69.02372843181458 , 37.07174680093543},
		{0.92 , 150.82523862591873 , 66.43187253398804 , 39.865929877158834},
		{0.9400000000000001 , 150.66475552662166 , 64.27706520007267 , 42.70030826211906},
		{0.96 , 150.4929365705137 , 62.623999448590766 , 45.58894173914527},
		{0.98 , 150.30491104056557 , 61.49723307358733 , 48.515037602779564},
		{1.0 , 150.1063964739498 , 60.925145913731996 , 51.4281756761645},
		{1.02 , 149.90969602560713 , 60.91429864823218 , 54.34221285514667},
		{1.04 , 149.71698271779525 , 61.454988592447 , 57.2393872202759},
		{1.06 , 149.53781325679824 , 62.52632934887107 , 60.08654189248641},
		{1.08 , 149.3777234785391 , 64.0875025395586 , 62.86855277883891},
		{1.1 , 149.2405400264756 , 66.0877292890027 , 65.57243542286837},
		{1.12 , 142.10619622359638 , 65.22207309188187 , 68.1419561072322},
		{1.1400000000000001 , 134.9996376806345 , 64.32863163485075 , 70.4985426902561},
		{1.16 , 128.01064748527978 , 63.317621830192216 , 72.66122833176185},
		{1.18 , 121.18175107272475 , 62.14651824274934 , 74.63607587375101},
		{1.2 , 114.54063107552685 , 60.787638239951406 , 76.43084180346},
		{1.22 , 108.08992013224835 , 59.23834918322712 , 78.08068753160552},
		{1.24 , 101.83250109898813 , 57.4957682164929 , 79.56682463683605},
		{1.26 , 95.76789464652842 , 55.560374668955404 , 80.91098574025446},
		{1.28 , 89.87361064495478 , 53.454658670522065 , 82.14549410709418},
		{1.3 , 84.1388648273046 , 51.18940448817364 , 83.25237222574796},
		{1.32 , 78.55457727085454 , 48.77369204462371 , 84.25046552799365},
		{1.34 , 73.09733581795291 , 46.230933497530756 , 85.16837585810738},
		{1.36 , 67.7555440032071 , 43.57272531227319 , 85.98790620135925},
		{1.3800000000000001 , 62.52032780296054 , 40.807941512518376 , 86.72501651308565},
		{1.4000000000000001 , 57.377542694705284 , 37.95072662077363 , 87.38367896462917},
		{1.42 , 52.31459994196591 , 35.013669373504854 , 87.97800252617907},
		{1.44 , 47.322286331802886 , 32.005982983671245 , 88.50034665007335},
		{1.46 , 42.39028291585581 , 28.93798639962567 , 88.97404361871773},
		{1.48 , 37.511080159404614 , 25.817189156069567 , 89.38065301010222},
		{1.5 , 27.889534519814664 , 16.493672113956904 , 89.73242133170434},
		{1.52 , 0.0 , 0.0 , 90.0},
		{1.54 , 0.0 , 0.0 , 90.0},
		{1.56 , 0.0 , 0.0 , 90.0},
		{1.58 , 0.0 , 0.0 , 90.0},
		{1.6 , 0.0 , 0.0 , 90.0},
		{1.62 , 0.0 , 0.0 , 90.0},
		{1.6400000000000001 , 0.0 , 0.0 , 90.0},
		{1.66 , 0.0 , 0.0 , 90.0},
		{1.68 , 0.0 , 0.0 , 90.0},
		{1.7 , 0.0 , 0.0 , 90.0},
		{1.72 , 0.0 , 0.0 , 90.0},
		{1.74 , 0.0 , 0.0 , 90.0},
		{1.76 , 0.0 , 0.0 , 90.0},
		{1.78 , 0.0 , 0.0 , 90.0},
		{1.8 , 0.0 , 0.0 , 90.0},
		{1.82 , 0.0 , 0.0 , 90.0},
		{1.84 , 0.0 , 0.0 , 90.0},
		{1.8599999999999999 , 0.0 , 0.0 , 90.0},
		{1.88 , 0.0 , 0.0 , 90.0},
		{1.9 , 0.0 , 0.0 , 90.0},
		{1.92 , 0.0 , 0.0 , 90.0},
		{1.94 , 0.0 , 0.0 , 90.0},
		{1.96 , 0.0 , 0.0 , 90.0},
		{1.98 , 0.0 , 0.0 , 90.0},
		{2.0 , 0.0 , 0.0 , 90.0},
		{2.02 , 0.0 , 0.0 , 90.0},
		{2.04 , 0.0 , 0.0 , 90.0},
		{2.06 , 0.0 , 0.0 , 90.0},
		{2.08 , 0.0 , 0.0 , 90.0},
		{2.1 , 0.0 , 0.0 , 90.0},
		{2.12 , 0.0 , 0.0 , 90.0},
		{2.14 , 0.0 , 0.0 , 90.0},
		{2.16 , 0.0 , 0.0 , 90.0},
		{2.18 , 0.0 , 0.0 , 90.0},
		{2.2 , 0.0 , 0.0 , 90.0},
		{2.2199999999999998 , 0.0 , 0.0 , 90.0},
		{2.24 , 0.0 , 0.0 , 90.0},
		{2.26 , 0.0 , 0.0 , 90.0},
		{2.2800000000000002 , 0.0 , 0.0 , 90.0},
		{2.3 , 0.0 , 0.0 , 90.0},
		{2.3200000000000003 , 0.0 , 0.0 , 90.0},
		{2.34 , 0.0 , 0.0 , 90.0},
		{2.36 , 0.0 , 0.0 , 90.0},
		{2.38 , 0.0 , 0.0 , 90.0},
		{2.4 , 0.0 , 0.0 , 90.0},
		{2.42 , 0.0 , 0.0 , 90.0},
		{2.44 , 0.0 , 0.0 , 90.0},
		{2.46 , 0.0 , 0.0 , 90.0},
		{2.48 , 0.0 , 0.0 , 90.0},
		{2.5 , 0.0 , 0.0 , 90.0},
		{2.52 , 0.23502590539787527 , -8.235025905397848 , 90.14343245082051},
		{2.54 , 0.4241776957850748 , -16.424177695785016 , 90.54338329087278},
		{2.56 , 0.49134585502891326 , -24.491345855028847 , 91.22332959146058},
		{2.58 , 0.36164297811353424 , -32.36164297811324 , 92.1743336470513},
		{2.6 , -0.13592136052330753 , -39.96914641120446 , 93.35661477614116},
		{2.62 , -0.7424839426188167 , -47.257516057381466 , 94.7561067203577},
		{2.64 , -1.817775609584185 , -54.182224390415946 , 96.33034162066974},
		{2.66 , -3.296611508169002 , -60.70338849183121 , 98.08806023719364},
		{2.68 , -5.216487881223869 , -66.78351211877629 , 100.00906740950529},
		{2.7 , -7.570704615372291 , -72.42929538462772 , 102.02555257546754},
		{2.72 , -10.332351355661249 , -77.66764864433884 , 104.12163901122562},
		{2.74 , -13.480687445346625 , -82.51931255465311 , 106.28204576451469},
		{2.76 , -16.983886589402754 , -87.01611341059741 , 108.49250760070112},
		{2.7800000000000002 , -20.79034173753157 , -91.20965826246768 , 110.72072116140629},
		{2.8 , -24.832873918799642 , -95.16712608120073 , 112.93974657555543},
		{2.82 , -29.06540640891153 , -98.93459359108938 , 115.16309077645298},
		{2.84 , -33.433692513051994 , -102.56630748694775 , 117.351915485088},
		{2.86 , -37.88770635516388 , -106.11229364483442 , 119.52337626551264},
		{2.88 , -42.401019829070336 , -109.59898017092866 , 121.67738830142781},
		{2.9 , -46.92587122327211 , -113.07412877672871 , 123.78555000770791},
		{2.92 , -51.43062015715303 , -116.56937984284608 , 125.86763147478212},
		{2.94 , -55.897344133856464 , -120.10265586614368 , 127.91424966744785},
		{2.96 , -60.311257121766666 , -123.68874287823546 , 129.9447758985114},
		{2.98 , -64.66101549819459 , -127.33898450180777 , 131.9514343674157},
		{3.0 , -68.93740425585753 , -131.06259574414239 , 133.95317869278904},
		{3.02 , -73.12958999157092 , -134.8704100084316 , 135.93116985835684},
		{3.04 , -77.22990670100958 , -138.77009329899133 , 137.904695282921},
		{3.06 , -81.24366330384385 , -142.7563366961584 , 139.89199332994815},
		{3.08 , -85.16542993343481 , -146.83457006656573 , 141.87569466739126},
		{3.1 , -88.15093048602601 , -149.58691546766536 , 143.8625721166162},
		{3.12 , -85.88055443130133 , -143.857291522391 , 145.79001158114937},
		{3.14 , -83.42028406573039 , -138.31756188796282 , 147.61866850110482},
		{3.16 , -80.8078920080396 , -132.92995394565588 , 149.36523583151666},
		{3.18 , -78.07776889760301 , -127.66007705608756 , 151.01025898158952},
		{3.2 , -75.25857748188635 , -122.4792684718046 , 152.59052374356747},
		{3.2199999999999998 , -72.37289628471554 , -117.36494966898063 , 154.0958865403761},
		{3.24 , -69.43623993196327 , -112.30160602173291 , 155.52695091753503},
		{3.26 , -66.46175582687351 , -107.27609012681519 , 156.89547547383984},
		{3.2800000000000002 , -63.4594377586418 , -102.27840819504588 , 158.20126163936743},
		{3.3 , -60.43650944351233 , -97.30133651018576 , 159.43193700371603},
		{3.3200000000000003 , -57.39890086830081 , -92.33894508539207 , 160.6104426146112},
		{3.34 , -54.35101413741244 , -87.38683181628458 , 161.72410019421477},
		{3.36 , -51.295917527624695 , -82.4419284260706 , 162.77193161184687},
		{3.38 , -48.23601398709735 , -77.50183196659451 , 163.76505110495918},
		{3.4 , -45.17294519446986 , -72.5649007592251 , 164.690395504373},
		{3.42 , -42.107838250484356 , -67.63000770321162 , 165.55919151622845},
		{3.44 , -39.04143650994431 , -62.696409443744095 , 166.35828155793638},
		{3.46 , -35.9741937404014 , -57.76365221329698 , 167.09907954384516},
		{3.48 , -32.90638160767819 , -52.83146434602048 , 167.78083566102225},
		{3.5 , -29.838113177802985 , -47.89973277588587 , 168.40285208826344},
		{3.52 , -26.769418853317358 , -42.9684271003769 , 168.964488929219},
		{3.54 , -23.700284462623895 , -38.03756149106704 , 169.46516816638973},
		{3.56 , -17.560136262378595 , -36.69640502592381 , 169.9043760375215}
	};
}