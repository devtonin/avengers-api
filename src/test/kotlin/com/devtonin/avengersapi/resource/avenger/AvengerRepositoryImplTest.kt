package com.devtonin.avengersapi.resource.avenger

import com.devtonin.avengersapi.domain.avenger.Avenger
import com.devtonin.avengersapi.domain.avenger.AvengerRepository
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AvengerRepositoryImplTest ( @Autowired private val repository: AvengerEntityRepository,  @Autowired private val avengerRepository: AvengerRepository) {

    @Test
    fun `getAvengers should return all avengers`() {
        // given
        val avenger1 = Avenger(1L, "Iron Man", "Hero", "Marvel", "history")
        val avenger2 = Avenger(2L, "Thor", "Hero", "Marvel", "history")
        val avenger3 = Avenger(3L, "Black Widow", "Hero", "Marvel", "history")
        val avenger4 = Avenger(4L, "Hulk", "Hero", "Marvel", "history")
        val avenger5 = Avenger(5L, "Ant-Man", "Hero", "Marvel", "history")
        val avengerEntities = listOf(
                AvengerEntity.from(avenger1),
                AvengerEntity.from(avenger2),
                AvengerEntity.from(avenger3),
                AvengerEntity.from(avenger4),
                AvengerEntity.from(avenger5)
        )
        mockkObject(repository)
        every { repository.findAll() } returns avengerEntities

        // when
        val result = avengerRepository.getAvengers()

        // then
        verify { repository.findAll() }
        assertThat(result).isEqualTo(avengerEntities.map { it.toAvenger() })
    }

}